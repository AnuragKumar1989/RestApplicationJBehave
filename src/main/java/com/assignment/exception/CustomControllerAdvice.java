package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;

/**
 * Advice class to perform certain action for certain exceptions.
 */
@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity handleNoResult(NoResultException nre) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(nre.getMessage(), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleBadRequest(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(e.getMessage(), status);
    }
}
