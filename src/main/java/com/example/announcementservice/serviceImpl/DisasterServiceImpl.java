package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.exception.DisasterNotFoundException;
import com.example.announcementservice.model.Disaster;
import com.example.announcementservice.repository.DisasterRepository;
import com.example.announcementservice.service.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisasterServiceImpl implements DisasterService {

    final
    DisasterRepository disasterRepository;

    public DisasterServiceImpl(DisasterRepository disasterRepository) {
        this.disasterRepository = disasterRepository;
    }

    @Override
    public Disaster getDisasterById(Long id) {
        return disasterRepository.findById(id).orElseThrow(
                DisasterNotFoundException::new
        );
    }
}
