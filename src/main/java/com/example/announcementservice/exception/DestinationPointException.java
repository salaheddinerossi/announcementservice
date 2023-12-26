package com.example.announcementservice.exception;

public class DestinationPointException extends RuntimeException{
    public DestinationPointException(){
        super("destination point not in the zone");
    }
}
