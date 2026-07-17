package com.abutua.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired 
    private CategoryRepository categoryRepository;

    public Category getById(int id) { 
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return category;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void update(int id, Category categoryUpdate) {
        Category category = getById(id);

        if (categoryUpdate.getName() == null || categoryUpdate.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name can not be empty");
        }

        category.setName(categoryUpdate.getName());
    

        categoryRepository.save(category);
    }
    
}
