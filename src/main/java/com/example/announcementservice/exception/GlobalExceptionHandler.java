package com.example.announcementservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(DisasterNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelDisasterNotFoundException(DisasterNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(StatusNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelStatusNotFoundException(StatusNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
