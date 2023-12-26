package com.example.announcementservice.repository;

import com.example.announcementservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    Optional<Organization> findByEmail(String email);
}
