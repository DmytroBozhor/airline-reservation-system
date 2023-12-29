package com.dmytrobozhor.airlinereservationservice.util.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ErrorDetail("The entity was not found. " + ex.getMessage(),
                new Date(), request.getDescription(true));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(fieldError -> errors.
                put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ErrorDetail(errors.toString(), new Date(), request.getDescription(true));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetail handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        return new ErrorDetail(ex.getMessage(), new Date(), request.getDescription(true));
    }


//    It is also possible to do like below
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorDetail> handleAccessDeniedException(
//            EntityNotFoundException ex, WebRequest request) {
//        return new ResponseEntity<>(new ErrorDetail("The entity was not found. " + ex.getMessage(),
//                new Date(), request.getDescription(true)), new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }


}
