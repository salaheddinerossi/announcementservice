package com.example.announcementservice.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "target")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long currentValue;

    private Long targetValue;


    @ManyToOne
    @JoinColumn(name ="announcement_id")
    Announcement announcement;

}
