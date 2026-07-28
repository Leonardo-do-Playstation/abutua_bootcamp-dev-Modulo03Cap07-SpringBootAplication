package com.abutua.productbackend.exceptions;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abutua.productbackend.services.exceptions.DataBaseException;

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



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> entityNotFoundException(EntityNotFoundException exception, HttpServletRequest request){
       

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandartError error = new StandartError();
        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatusCode(status.value());
        error.setTimeStamp(Instant.now());


      
       
        return ResponseEntity.status(status).body(error);
    }

}
