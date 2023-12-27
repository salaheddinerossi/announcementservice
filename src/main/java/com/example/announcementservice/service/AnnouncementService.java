package com.example.announcementservice.service;

import com.example.announcementservice.dto.AnnouncementDto;
import com.example.announcementservice.model.Organization;
import com.example.announcementservice.response.AnnouncementResponse;
import com.example.announcementservice.response.AnnouncementResponseDisaster;

import java.util.List;

public interface AnnouncementService {

    public AnnouncementDto createAnnouncement(AnnouncementDto announcementDto, Organization organization);

    public AnnouncementResponse getAnnouncement(Long id);

    public List<AnnouncementResponseDisaster> getAnnouncementsByDisaster(Long id);

}
