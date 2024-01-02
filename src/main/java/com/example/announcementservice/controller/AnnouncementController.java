package com.example.announcementservice.controller;


import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.dto.AnnouncementStatus;
import com.example.announcementservice.exception.NoAuthorizationException;
import com.example.announcementservice.model.Announcement;
import com.example.announcementservice.model.Organization;
import com.example.announcementservice.response.AnnouncementResponseDisaster;
import com.example.announcementservice.service.AnnouncementService;
import com.example.announcementservice.service.OrganizationService;
import com.example.announcementservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    @Value("${other.service.url}")
    private String authServiceUrl;

    final
    AnnouncementService announcementService;

    final
    UserService userService;

    final
    OrganizationService organizationService;

    public AnnouncementController(AnnouncementService announcementService, UserService userService, OrganizationService organizationService) {
        this.announcementService = announcementService;
        this.userService = userService;
        this.organizationService = organizationService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnnouncement(@Valid @RequestBody  AnnouncementDto announcementDto, @RequestHeader("Authorization") String token){
        Boolean isOrganization = userService.isOrganization(token,authServiceUrl);

        if(isOrganization){

            String OrganizationEmail = userService.getEmail(token,authServiceUrl);
            Organization organization = organizationService.getOrganizationByEmail(OrganizationEmail);

            Boolean hasAuthorisation = organizationService.hasAuthorization(organization,announcementDto.getAuthorization_id());

            if (hasAuthorisation){

                return ResponseEntity.status(HttpStatus.OK).body(announcementService.createAnnouncement(announcementDto,organization)) ;

            }

            throw new NoAuthorizationException();

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not logged in");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnnouncementById(@PathVariable Long id ){
        return ResponseEntity.status(HttpStatus.OK).body(
                announcementService.getAnnouncement(id)
        );
    }

    @GetMapping("/disaster/{id}")
    public ResponseEntity<?> getAnnouncementsByDisaster(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(
                announcementService.getAnnouncementsByDisaster(id)
        );
    }

    @GetMapping("/disaster/all/{id}")
    public ResponseEntity<?> getDisasterWithAnnouncements(@PathVariable Long id,@RequestHeader("Authorization") String token){
        Boolean isOrganization = userService.isOrganization(token,authServiceUrl);

        if(isOrganization){
            return ResponseEntity.status(HttpStatus.OK).body(
                    announcementService.getDisasterWithAnnouncement(id)
            );

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not allowed to access this page");
    }

    @PatchMapping("/")
    public ResponseEntity<?> changeStatus(@Valid @RequestBody AnnouncementStatus announcementStatus,@RequestHeader("Authorization") String token){
        Boolean isOrganization = userService.isOrganization(token,authServiceUrl);

        String email = userService.getEmail(token,authServiceUrl);

        Announcement announcement = announcementService.getAnnouncementById(announcementStatus.getId());

        if (isOrganization){
            if(Objects.equals(announcement.getOrganization().getEmail(), email)){
                announcementService.changeAnnouncementStatus(announcementStatus.getId(),announcementStatus.getStatus());
                return ResponseEntity.status(HttpStatus.OK).body("status has been changed ");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not the owner of this announcement ");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not allowed to perform this action please log in ");
    }

    @GetMapping("/organization")
    public ResponseEntity<?> getAnnouncementsByOrganization(@RequestHeader("Authorization") String token){
        Boolean isOrganization = userService.isOrganization(token,authServiceUrl);

        if (isOrganization){

            String OrganizationEmail = userService.getEmail(token,authServiceUrl);
            Organization organization = organizationService.getOrganizationByEmail(OrganizationEmail);

            List<AnnouncementResponseDisaster> announcementResponseDisasters = announcementService.getAnnouncementsByOrganizationId(organization.getId());

            return ResponseEntity.status(HttpStatus.OK).body(announcementResponseDisasters);

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you cannot access this data");
    }

}
