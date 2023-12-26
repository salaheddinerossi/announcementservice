package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.exception.OrganizationNotFoundException;
import com.example.announcementservice.model.Authorization;
import com.example.announcementservice.model.Organization;
import com.example.announcementservice.model.OrganizationAuthorization;
import com.example.announcementservice.repository.OrganizationRepository;
import com.example.announcementservice.service.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    final
    OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization getOrganizationByEmail(String email) {
        return organizationRepository.findByEmail(email).orElseThrow(
                OrganizationNotFoundException::new
        );
    }

    @Override
    public Boolean hasAuthorization(Organization organization, Long authorizationId) {

        if(organization.getOrganizationAuthorizations()!=null ){
            if(!organization.getOrganizationAuthorizations().isEmpty()){
                for(OrganizationAuthorization organizationAuthorization : organization.getOrganizationAuthorizations()){
                    if(Objects.equals(organizationAuthorization.getAuthorization().getId(), authorizationId)){
                        return true;
                    }
                }
            }
        }

        return false;
    }


}
