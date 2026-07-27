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
    public ResponseEntity<ValidationErrors> validationException(MethodArgumentNotValidException exception, HttpServletRequest request){
       

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationErrors error = new ValidationErrors();
        error.setError("Validation Error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatusCode(status.value());
        error.setTimeStamp(Instant.now());


        exception.getBindingResult()
                .getFieldErrors()
                .forEach(e -> error.addError(e.getDefaultMessage()));
       
        return ResponseEntity.status(status).body(error);
    }

}
