package com.example.announcementservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity(name = "disaster")
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private String description;

    private LocalDate date;

    private Boolean isActive;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon mainZone;


    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("disaster")
    private List<Zone> zones = new ArrayList<>();

}
