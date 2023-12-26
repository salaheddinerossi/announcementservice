package com.example.announcementservice.repository;

import com.example.announcementservice.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization,Long> {
}
