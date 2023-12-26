package com.example.announcementservice.serviceImpl;


import com.example.announcementservice.exception.ZoneNotFoundException;
import com.example.announcementservice.model.Zone;
import com.example.announcementservice.repository.ZoneRepository;
import com.example.announcementservice.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneServiceImpl implements ZoneService {

    final
    ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone getZoneById(Long id){

        return zoneRepository.findById(id).orElseThrow(
                ZoneNotFoundException::new
        );

    }
}
