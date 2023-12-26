package com.example.announcementservice.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organization_authorization")
public class OrganizationAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dedicated_paper")
    private String dedicatedPaper;

    @ManyToOne
    @JoinColumn(name = "authorization_id" , referencedColumnName = "id")
    Authorization authorization;

    @ManyToOne
    @JoinColumn(name = "organization_id",referencedColumnName = "id")
    Organization organization;

}
