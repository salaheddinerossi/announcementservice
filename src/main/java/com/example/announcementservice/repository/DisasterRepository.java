package com.example.announcementservice.repository;

import com.example.announcementservice.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterRepository extends JpaRepository<Disaster,Long> {
}
