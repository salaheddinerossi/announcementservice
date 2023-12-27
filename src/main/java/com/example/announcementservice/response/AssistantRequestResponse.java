package com.example.announcementservice.response;


import com.example.announcementservice.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;


@Data
public class AssistantRequestResponse {

    private Long id;


    private String expressNeeds;

    @JsonSerialize(using = PointSerializer.class)
    private Point localisation;

}
