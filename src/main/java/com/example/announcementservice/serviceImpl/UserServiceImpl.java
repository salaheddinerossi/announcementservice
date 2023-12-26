package com.example.announcementservice.serviceImpl;

import com.example.announcementservice.dto.UserDetailsDto;
import com.example.announcementservice.exception.TokenNotValidException;
import com.example.announcementservice.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetailsDto getUserDetailsFromOtherService(String serviceUrl, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Set the token
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try{
            ResponseEntity<UserDetailsDto> response = restTemplate.exchange(
                    serviceUrl,
                    HttpMethod.GET,
                    entity,
                    UserDetailsDto.class
            );
            return response.getBody();

        }catch (HttpClientErrorException.Unauthorized ex){
            throw new TokenNotValidException();
        }
    }

    @Override
    public boolean isAdmin(String token, String url) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        UserDetailsDto userDetails = this.getUserDetailsFromOtherService(url, actualToken);
        return userDetails.getRole().equals("ROLE_ADMIN");

    }

    @Override
    public boolean isOrganization(String token, String url) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        UserDetailsDto userDetails = this.getUserDetailsFromOtherService(url, actualToken);
        return userDetails.getRole().equals("ROLE_ORGANIZATION");
    }

    @Override
    public String getEmail(String token, String url) {
        String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        UserDetailsDto userDetails = this.getUserDetailsFromOtherService(url, actualToken);
        return userDetails.getEmail();
    }
}
