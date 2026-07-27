package com.abutua.productbackend.dtos;

import javax.validation.constraints.Min;

public class IntegerDTO {
   
    @Min(value = 1, message = "Id must be greater than 0")
    Long id;
    
    public IntegerDTO(Long id) {
        this.id = id;
    }

    public IntegerDTO(){ 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
