package com.abutua.productbackend.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validationException(MethodArgumentNotValidException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error");
    }

}
