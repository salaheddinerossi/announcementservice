package com.example.announcementservice.repository;

import com.example.announcementservice.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    public Optional<List<Announcement>> findByZoneDisasterId(Long id);


}
