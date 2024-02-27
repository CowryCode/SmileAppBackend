package com.cowrycode.smileapp.config;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointer(NullPointerException e) {
        // Log the exception if needed
        return new ResponseEntity<>("Handled by Central Null Pointer @ " + LocalDateTime.now() + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchMethodError.class)
    public ResponseEntity<String> handleNoSuchMethodError(NoSuchMethodError ex) {
        // Log the exception if needed
        return new ResponseEntity<>("Handled by Central NoSuchMethodError @ " + LocalDateTime.now() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<String> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
        // Log the exception if needed
        return new ResponseEntity<>("Handled by Central InvalidDataAccessApiUsageException @ " + LocalDateTime.now() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        // Log the exception if needed
        return new ResponseEntity<>("Handled by Central CustomException @ " + LocalDateTime.now() + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

