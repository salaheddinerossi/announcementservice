package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.dto.TargetDto;
import com.example.announcementservice.enums.Status;
import com.example.announcementservice.exception.AnnouncementNotFoundException;
import com.example.announcementservice.exception.DestinationPointException;
import com.example.announcementservice.exception.DisasterNotFoundException;
import com.example.announcementservice.model.*;
import com.example.announcementservice.repository.AnnouncementRepository;
import com.example.announcementservice.response.*;
import com.example.announcementservice.service.*;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    final
    DisasterService disasterService;

    final
    GeometryService geometryService;

    final
    AnnouncementRepository announcementRepository;

    final
    AuthorizationService authorizationService;

    final ZoneService zoneService;

    public AnnouncementServiceImpl(GeometryService geometryService, AnnouncementRepository announcementRepository, AuthorizationService authorizationService, ZoneService zoneService, DisasterService disasterService) {
        this.geometryService = geometryService;
        this.announcementRepository = announcementRepository;
        this.authorizationService = authorizationService;
        this.zoneService = zoneService;
        this.disasterService = disasterService;
    }


    @Transactional
    @Override
    public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto, Organization organization) {

        Announcement announcement = new Announcement();

        announcement.setImage(announcementDto.getImage());
        announcement.setTitle(announcementDto.getTitle());
        announcement.setImage(announcementDto.getImage());
        announcement.setDescription(announcementDto.getDescription());
        announcement.setOrganization(organization);

        Point arrivePoint= geometryService.createPoint(announcementDto.getArrivePoint());


        announcement.setDepartPoint(geometryService.createPoint(announcementDto.getDepartPoint()));
        announcement.setArrivePoint(arrivePoint);

        Zone announcementZone = zoneService.getZoneById(announcementDto.getZone_id());


        if (!geometryService.isPointInZone(arrivePoint,announcementZone)){
            throw  new DestinationPointException();
        }

        announcement.setZone(announcementZone);

        Authorization authorization = authorizationService.getAuthorization(announcementDto.getAuthorization_id());
        announcement.setAuthorization(authorization);

        List<Target> targets = new ArrayList<>();

        for(TargetDto targetDto : announcementDto.getTargetsDto()){
            Target target = new Target();
            target.setTargetValue(targetDto.getTargetValue());
            target.setName(targetDto.getName());
            target.setCurrentValue(0L);
            target.setAnnouncement(announcement);
            targets.add(target);
        }

        announcement.setTargets(targets);

        announcement.setStatus(Status.ACTIVE);

        Announcement announcement1 = announcementRepository.save(announcement);

        announcementDto.setId(announcement1.getId());

        return announcementDto;
    }

    @Override
    public AnnouncementResponse getAnnouncement(Long id) {

        Announcement announcement = announcementRepository.findById(id).orElseThrow(
                AnnouncementNotFoundException::new
        );

        AnnouncementResponse announcementResponse = new AnnouncementResponse();

        announcementResponse.setId(announcement.getId());
        announcementResponse.setImage(announcement.getImage());
        announcementResponse.setStatus(String.valueOf(announcement.getStatus()));
        announcementResponse.setTitle(announcement.getTitle());
        announcementResponse.setDescription(announcement.getDescription());
        announcementResponse.setArrivePoint(announcement.getArrivePoint());
        announcementResponse.setDepartPoint(announcement.getDepartPoint());

        AuthorizationResponse authorizationResponse = new AuthorizationResponse();

        authorizationResponse.setName(announcement.getAuthorization().getName());
        authorizationResponse.setId(announcement.getAuthorization().getId());

        announcementResponse.setAuthorizationResponse(authorizationResponse);

        OrganizationResponse organizationResponse = new OrganizationResponse();

        organizationResponse.setId(announcement.getOrganization().getId());
        organizationResponse.setName(announcement.getOrganization().getName());

        announcementResponse.setOrganizationResponse(organizationResponse);

        for (Target target : announcement.getTargets()){

            TargetResponse targetResponse = new TargetResponse();

            targetResponse.setName(target.getName());
            targetResponse.setId(target.getId());
            targetResponse.setId(target.getId());
            targetResponse.setTargetValue(target.getTargetValue());
            targetResponse.setTargetValue(target.getTargetValue());

            announcementResponse.getTargetResponses().add(targetResponse);

        }

        return announcementResponse;
    }

    @Override
    public List<AnnouncementResponseDisaster> getAnnouncementsByDisaster(Long id) {

        List<Announcement> announcements = announcementRepository.findByZoneDisasterId(id).orElseThrow(
                DisasterNotFoundException::new
        );


        
        List<AnnouncementResponseDisaster> announcementResponseDisasters = new ArrayList<>();
        for (Announcement announcement : announcements){
            
            AnnouncementResponseDisaster announcementResponseDisaster = new AnnouncementResponseDisaster();

            List<TargetResponse> targetResponses = getTargetResponses(announcement);

            announcementResponseDisaster.setTargetResponses(targetResponses);
            announcementResponseDisaster.setTitle(announcement.getTitle());
            announcementResponseDisaster.setImage(announcement.getImage());
            announcementResponseDisaster.setId(announcement.getId());
            announcementResponseDisaster.setDescription(announcement.getDescription());

            Authorization authorization = announcement.getAuthorization();

            AuthorizationResponse authorizationResponse = new AuthorizationResponse();

            authorizationResponse.setId(authorization.getId());
            authorizationResponse.setName(authorization.getName());

            announcementResponseDisaster.setAuthorizationResponse(authorizationResponse);

            announcementResponseDisaster.setDisasterName(
                    announcement.getZone().getDisaster().getName()
            );

            announcementResponseDisasters.add(announcementResponseDisaster);
        }

        return announcementResponseDisasters;

    }

    @Override
    public DisasterResponse getDisasterWithAnnouncement(Long id) {

        Disaster disaster = disasterService.getDisasterById(id);

        DisasterResponse disasterResponse= new DisasterResponse();

        disasterResponse.setName(disaster.getName());
        disasterResponse.setMainZone(disaster.getMainZone());

        List<ZoneResponse> zoneResponses = getZoneResponses(disaster);
        disasterResponse.setZoneResponses(zoneResponses);

        return disasterResponse;
    }

    private static List<TargetResponse> getTargetResponses(Announcement announcement) {
        List<TargetResponse> targetResponses = new ArrayList<>();

        for (Target target : announcement.getTargets()){

            TargetResponse targetResponse = new TargetResponse();

            targetResponse.setName(target.getName());
            targetResponse.setCurrentValue(target.getCurrentValue());
            targetResponse.setTargetValue(target.getTargetValue());
            targetResponse.setId(target.getId());

            targetResponses.add(targetResponse);
        }
        return targetResponses;
    }

    private static List<ZoneResponse> getZoneResponses(Disaster disaster){
        List<ZoneResponse> zoneResponses = new ArrayList<>();

        for (Zone zone : disaster.getZones()){

            ZoneResponse zoneResponse = new ZoneResponse();

            zoneResponse.setGeometry(zone.getGeometry());
            zoneResponse.setName(zone.getName());
            zoneResponse.setId(zone.getId());
            zoneResponse.setAssistantRequestResponses(getAssistantRequestResponses(zone));
            zoneResponse.setAnnouncementOrganizationResponses(getAnnouncementOrganizationResponses(zone));

            zoneResponses.add(zoneResponse);
        }
        return zoneResponses;
    }

    private static List<AssistantRequestResponse> getAssistantRequestResponses(Zone zone){
        List<AssistantRequestResponse> assistantRequestResponses = new ArrayList<>();

        for(AssistanceRequest assistanceRequest : zone.getAssistanceRequests()){
            AssistantRequestResponse assistantRequestResponse = new AssistantRequestResponse();

            assistantRequestResponse.setId(assistanceRequest.getId());
            assistantRequestResponse.setLocalisation(assistanceRequest.getLocalisation());
            assistantRequestResponse.setExpressNeeds(assistanceRequest.getExpressNeeds());

            assistantRequestResponses.add(assistantRequestResponse);
        }
        return assistantRequestResponses;
    }

    private static List<AnnouncementOrganizationResponse> getAnnouncementOrganizationResponses(Zone zone){
        List<AnnouncementOrganizationResponse> announcementOrganizationResponses = new ArrayList<>();

        for (Announcement announcement : zone.getAnnouncements()){
            AnnouncementOrganizationResponse announcementOrganizationResponse = new AnnouncementOrganizationResponse();

            announcementOrganizationResponse.setOrganizationId(announcement.getOrganization().getId());
            announcementOrganizationResponse.setStatus(String.valueOf(announcement.getStatus()));
            announcementOrganizationResponse.setTitle(announcement.getTitle());
            announcementOrganizationResponse.setId(announcement.getId());
            announcementOrganizationResponse.setArrivePoint(announcement.getArrivePoint());

            announcementOrganizationResponses.add(announcementOrganizationResponse);
        }
        return announcementOrganizationResponses;
    }
}
