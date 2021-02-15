package com.embl.test.restfulmicroservice.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.embl.test.restfulmicroservice.security.InvalidJwtAuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorModel.of(new Date(), ex.getMessage(), request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorModel.of(new Date(), "Validation Failed",
                ex.getBindingResult().getFieldError().getDefaultMessage()),
                HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = {InvalidJwtAuthenticationException.class})
    public ResponseEntity<Object> invalidJwtAuthentication(InvalidJwtAuthenticationException ex, WebRequest request) {
        log.debug("handling InvalidJwtAuthenticationException...");
        return new ResponseEntity<>(ErrorModel.of(new Date(), ex.getMessage(), request.getDescription(false)),
                HttpStatus.UNAUTHORIZED);
    }

}
