package com.dmytrobozhor.airlinereservationservice.util.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ErrorDetail("The entity was not found. " + ex.getMessage(),
                new Date(), request.getDescription(true));
    }


}
