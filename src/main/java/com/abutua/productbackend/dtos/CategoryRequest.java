package com.abutua.productbackend.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.abutua.productbackend.models.Category;

public class CategoryRequest {
    
    @Column(nullable = false, unique = true, length = 255)
     @NotBlank(message = "Name can't be null")
    @Size(min=3, max=255, message = "Name must be between 3 and 255 characters")

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category toEntity(){
        return new Category(name);
    }

}
