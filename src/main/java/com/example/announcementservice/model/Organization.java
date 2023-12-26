package com.example.announcementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String email;

    private String password;

    private String description;

    private String documents;

    private Boolean isActive;

    @OneToMany(mappedBy = "organization")
    private List<OrganizationAuthorization> organizationAuthorizations;

}
