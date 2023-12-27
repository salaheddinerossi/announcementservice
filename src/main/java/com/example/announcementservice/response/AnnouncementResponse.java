package com.example.announcementservice.response;


import com.example.announcementservice.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnnouncementResponse {

    private Long id;

    private String title;

    private String description;

    private String image;

    private String status;

    @JsonSerialize(using = PointSerializer.class)
    private Point departPoint;

    @JsonSerialize(using = PointSerializer.class)
    private Point arrivePoint;

    private AuthorizationResponse authorizationResponse;

    private List<TargetResponse> targetResponses = new ArrayList<>();

    private OrganizationResponse organizationResponse;

}
