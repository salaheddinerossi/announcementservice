package com.example.announcementservice.exception;

public class AuthorizationNotFoundException extends RuntimeException {
    public AuthorizationNotFoundException(){
        super("authorization not found");
    }
}
