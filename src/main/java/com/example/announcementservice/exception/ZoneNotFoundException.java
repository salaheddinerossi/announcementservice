package com.example.announcementservice.exception;

public class ZoneNotFoundException extends RuntimeException{
    public ZoneNotFoundException(){
        super("zone not found");
    }
}
