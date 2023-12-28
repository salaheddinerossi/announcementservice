package com.example.announcementservice.service;

import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.model.Announcement;
import com.example.announcementservice.model.Organization;
import com.example.announcementservice.response.AnnouncementResponse;
import com.example.announcementservice.response.AnnouncementResponseDisaster;
import com.example.announcementservice.response.DisasterResponse;

import java.util.List;

public interface AnnouncementService {

    AnnouncementDto createAnnouncement(AnnouncementDto announcementDto, Organization organization);

    AnnouncementResponse getAnnouncement(Long id);

    List<AnnouncementResponseDisaster> getAnnouncementsByDisaster(Long id);

    DisasterResponse getDisasterWithAnnouncement(Long id);

    void changeAnnouncementStatus(Long id,String status);

    Announcement getAnnouncementById(Long id);

    List<AnnouncementResponseDisaster> getAnnouncementsByOrganizationId(Long id);


}
