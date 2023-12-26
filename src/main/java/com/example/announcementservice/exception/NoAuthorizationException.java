package com.example.announcementservice.exception;

public class NoAuthorizationException extends RuntimeException {
    public NoAuthorizationException(){
        super("you don't have this authorization you need to provide the paper to the admin for this authorization");
    }
}
