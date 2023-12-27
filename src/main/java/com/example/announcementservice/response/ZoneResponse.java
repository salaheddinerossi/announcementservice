package com.example.announcementservice.response;

import com.example.announcementservice.serialization.PolygonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

@Data
public class ZoneResponse {

    private Long id;

    private String name;

    @JsonSerialize(using = PolygonSerializer.class)
    private Polygon geometry;

    List<AssistantRequestResponse> assistantRequestResponses;

    List<AnnouncementOrganizationResponse> announcementOrganizationResponses;

}
