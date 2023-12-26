package com.example.announcementservice.dto;


import lombok.Data;

import java.util.List;

@Data
public class AnnouncementDto {

    private Long id;

    private String title;

    private String description;

    private PointDto departPoint;

    private PointDto arrivePoint;

    private String image;

    private Long zone_id;

    private Long authorization_id;

    private Long organization_id;

    private List<TargetDto> targetsDto;

}
