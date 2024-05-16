package com.seshu.ems.error_handlers;

import com.seshu.ems.custom_exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var error = ErrorResponse.builder(
                new Throwable("Resource Not Found"), HttpStatus.NOT_FOUND, ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
