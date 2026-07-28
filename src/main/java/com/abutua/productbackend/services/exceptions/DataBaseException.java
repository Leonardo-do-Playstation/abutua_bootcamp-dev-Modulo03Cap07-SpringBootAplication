package com.abutua.productbackend.services.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abutua.productbackend.exceptions.StandartError;

public class DataBaseException extends RuntimeException{
    
    public DataBaseException(String message) {
        super(message);
    } 

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandartError> dataBaseException(DataBaseException exception, HttpServletRequest request){
       

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        StandartError error = new StandartError();

        error.setError("Database Error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatusCode(status.value());
        error.setTimeStamp(Instant.now());
       
        return ResponseEntity.status(status).body(error);
    }


}
