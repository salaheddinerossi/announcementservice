package com.example.announcementservice.controller;


import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.exception.NoAuthorizationException;
import com.example.announcementservice.model.Organization;
import com.example.announcementservice.service.AnnouncementService;
import com.example.announcementservice.service.OrganizationService;
import com.example.announcementservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    @Value("${other.service.url}")
    private String authService;

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
    public ResponseEntity<?> createAnnouncement(@RequestBody  AnnouncementDto announcementDto, @RequestHeader("Authorization") String token){
        Boolean isOrganization = userService.isOrganization(token,authService);

        if(isOrganization){

            String OrganizationEmail = userService.getEmail(token,authService);
            Organization organization = organizationService.getOrganizationByEmail(OrganizationEmail);

            Boolean hasAuthorisation = organizationService.hasAuthorization(organization,announcementDto.getAuthorization_id());

            System.out.println(announcementDto);
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
        Boolean isOrganization = userService.isOrganization(token,authService);

        if(isOrganization){
            return ResponseEntity.status(HttpStatus.OK).body(
                    announcementService.getDisasterWithAnnouncement(id)
            );

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("you are not allowed to access this page");
    }


}
