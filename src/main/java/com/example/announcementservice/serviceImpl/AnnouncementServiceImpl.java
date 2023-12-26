package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.dto.TargetDto;
import com.example.announcementservice.enums.Status;
import com.example.announcementservice.exception.DestinationPointException;
import com.example.announcementservice.model.*;
import com.example.announcementservice.repository.AnnouncementRepository;
import com.example.announcementservice.response.AnnouncementResponse;
import com.example.announcementservice.service.AnnouncementService;
import com.example.announcementservice.service.AuthorizationService;
import com.example.announcementservice.service.GeometryService;
import com.example.announcementservice.service.ZoneService;
import jakarta.transaction.Transactional;
import org.geolatte.geom.codec.support.DecodeException;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    final
    GeometryService geometryService;

    final
    AnnouncementRepository announcementRepository;

    final
    AuthorizationService authorizationService;

    final ZoneService zoneService;

    public AnnouncementServiceImpl(GeometryService geometryService, AnnouncementRepository announcementRepository, AuthorizationService authorizationService, ZoneService zoneService) {
        this.geometryService = geometryService;
        this.announcementRepository = announcementRepository;
        this.authorizationService = authorizationService;
        this.zoneService = zoneService;
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
            target.setCurrentValue(target.getCurrentValue());
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
        return null;
    }
}
