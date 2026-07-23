package com.abutua.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.productbackend.dtos.CategoryRequest;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.services.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin
@RequestMapping("categories")
public class CategoryController {
  
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public List<Category> getCategories() { 
        return categoryService.getAll();
    }

     @PostMapping
    public ResponseEntity<Category> save(@Validated @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
 
        return ResponseEntity.created(location).body(category);
    }

    @PutMapping("{id}")    
    public ResponseEntity<Void> updateProduct(@PathVariable long id, @RequestBody Category categoryUpdate) {
        categoryService.update(id, categoryUpdate);
        return ResponseEntity.ok().build(); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
      categoryService.deleteById(id);
      return ResponseEntity.noContent().build();
    }
}
