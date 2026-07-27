package com.abutua.productbackend.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler{
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> validationException(MethodArgumentNotValidException e, HttpServletRequest request){
       
        StandartError error = new StandartError();
        error.setError("Validation Error");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(Instant.now());

       
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
