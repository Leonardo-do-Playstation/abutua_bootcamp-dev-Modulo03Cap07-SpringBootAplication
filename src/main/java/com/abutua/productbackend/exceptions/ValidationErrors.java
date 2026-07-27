package com.abutua.productbackend.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandartError {
    
    private List<String> errors = new ArrayList<>();

    public void addError(String error){
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }

}
