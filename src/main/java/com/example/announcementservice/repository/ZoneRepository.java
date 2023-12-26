package com.example.announcementservice.repository;

import com.example.announcementservice.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone,Long> {
}
