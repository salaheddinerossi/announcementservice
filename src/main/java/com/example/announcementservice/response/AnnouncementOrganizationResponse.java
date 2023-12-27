package com.example.announcementservice.response;

import com.example.announcementservice.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class AnnouncementOrganizationResponse {

    private Long id;

    private String title;

    private String status;

    private Long organizationId;

    @JsonSerialize(using = PointSerializer.class)
    private Point arrivePoint;

}
