package com.example.announcementservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handelTokenNotValidException(TokenNotValidException e){
        return e.getMessage();
    }

    @ExceptionHandler(OrganizationNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelOrganizationNotFoundException(OrganizationNotFoundException e){
        return e.getMessage();
    }


    @ExceptionHandler(NoAuthorizationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelNoAuthorizationException(NoAuthorizationException e){
        return e.getMessage();
    }

    @ExceptionHandler(AuthorizationNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelAuthorizationNotFoundException(AuthorizationNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ZoneNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelZoneNotFoundException(ZoneNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(DestinationPointException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelDestinationPointException(DestinationPointException e){
        return e.getMessage();
    }

    @ExceptionHandler(AnnouncementNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelAnnouncementNotFound(AnnouncementNotFoundException e){
        return e.getMessage();
    }



}
