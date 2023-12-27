package com.example.announcementservice.exception;

public class AnnouncementNotFoundException extends RuntimeException{

    public AnnouncementNotFoundException(){
        super("announcement not found");
    }
}
