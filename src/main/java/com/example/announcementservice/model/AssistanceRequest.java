package com.example.announcementservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;


@Data
@Entity(name = "rssistance_request")
public class AssistanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String phone;

    @Column(columnDefinition = "geometry(Point,4326)")
    private Point localisation;

    private String address;

    private String expressNeeds;

    @ManyToOne
    @JoinColumn(name="zone_id")
    private Zone zone;

}
