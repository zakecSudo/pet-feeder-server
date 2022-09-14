package com.degree.petFeeder.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleApiErrorException(ApiException e) {
        return new ResponseEntity<>(e.getApiError(), HttpStatus.BAD_REQUEST);
    }

}
