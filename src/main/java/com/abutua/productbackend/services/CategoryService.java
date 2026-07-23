package com.abutua.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dtos.CategoryRequest;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired 
    private CategoryRepository categoryRepository;

    public Category getById(long id) { 
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(long id){
        categoryRepository.deleteById(id);
    }

    public Category save(CategoryRequest categoryRequest){
        return categoryRepository.save(categoryRequest.toEntity());
    }

    public void update(long id, Category categoryUpdate) {
        Category category = getById(id);

        if (categoryUpdate.getName() == null || categoryUpdate.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name can not be empty");
        }

        category.setName(categoryUpdate.getName());
    

        categoryRepository.save(category);
    }
    
}
