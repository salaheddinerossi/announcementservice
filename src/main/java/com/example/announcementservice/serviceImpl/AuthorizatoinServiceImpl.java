package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.exception.AuthorizationNotFoundException;
import com.example.announcementservice.model.Authorization;
import com.example.announcementservice.repository.AuthorizationRepository;
import com.example.announcementservice.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizatoinServiceImpl implements AuthorizationService {

    final
    AuthorizationRepository authorizationRepository;

    public AuthorizatoinServiceImpl(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Authorization getAuthorization(Long id) {
        return authorizationRepository.findById(id).orElseThrow(
                AuthorizationNotFoundException::new
        );
    }
}
