package com.example.announcementservice.exception;

public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException(){
        super("token not valid");
    }
}
