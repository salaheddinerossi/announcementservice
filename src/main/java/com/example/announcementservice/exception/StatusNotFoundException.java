package com.example.announcementservice.exception;

public class StatusNotFoundException extends RuntimeException{
    public StatusNotFoundException(){
        super("status doesn't exist");
    }
}
