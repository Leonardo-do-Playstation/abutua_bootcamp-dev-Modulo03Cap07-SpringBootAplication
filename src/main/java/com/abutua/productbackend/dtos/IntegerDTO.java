package com.abutua.productbackend.dtos;

public class IntegerDTO {
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
