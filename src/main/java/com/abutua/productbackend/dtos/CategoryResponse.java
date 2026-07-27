package com.abutua.productbackend.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL) // Para nao retornar null no nome da categoria no JSON.
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse() {
    
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



}

