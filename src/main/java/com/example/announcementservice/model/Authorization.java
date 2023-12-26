package com.example.announcementservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "\"authorization\"")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String requiredPaper;


    @OneToMany(mappedBy = "authorization",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Announcement> announcements;

}
