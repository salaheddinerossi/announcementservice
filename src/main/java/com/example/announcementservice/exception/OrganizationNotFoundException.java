package com.example.announcementservice.exception;

public class OrganizationNotFoundException extends RuntimeException{
    public OrganizationNotFoundException(){
        super("organization not found");
    }
}
