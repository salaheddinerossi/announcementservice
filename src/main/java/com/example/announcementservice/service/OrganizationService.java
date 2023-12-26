package com.example.announcementservice.service;

import com.example.announcementservice.model.Organization;

public interface OrganizationService {

    public Organization getOrganizationByEmail(String email);

    public Boolean hasAuthorization(Organization organization ,Long authorizationId);

}
