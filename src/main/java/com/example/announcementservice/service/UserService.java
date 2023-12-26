package com.example.announcementservice.service;

import com.example.announcementservice.dto.UserDetailsDto;

public interface UserService {
    public UserDetailsDto getUserDetailsFromOtherService(String serviceUrl, String token);


    public boolean isAdmin(String token, String url);

    public boolean isOrganization(String token, String url);

    public String getEmail(String token, String url);
}
