package com.example.announcementservice.model;


import com.example.announcementservice.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Data
@Entity(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Point departPoint;

    private Point arrivePoint;

    private String image;

    @ManyToOne
    @JoinColumn(name = "authorization_id", referencedColumnName = "id")
    private Authorization authorization;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name ="organization_id")
    Organization organization;

    @ManyToOne
    @JoinColumn(name ="zone_id")
    Zone zone;

    @OneToMany(mappedBy = "announcement",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Target> targets;





}
