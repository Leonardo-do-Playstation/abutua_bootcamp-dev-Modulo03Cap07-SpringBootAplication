package com.abutua.productbackend.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dtos.ProductRequest;
import com.abutua.productbackend.dtos.ProductResponse;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponse getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        return product.toDto();
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(Product::toDto).collect(java.util.stream.Collectors.toList());
    }

    public ProductResponse save(ProductRequest productRequest) {
       
       try{
        Product newProduct = productRepository.save(productRequest.toEntity());
        return newProduct.toDto();
       } catch (DataIntegrityViolationException e){
            throw new EntityNotFoundException("Constraint violation - Category not found");
       }
    }

    public void deleteById(long id) {
        
        try{
            productRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
                throw new EntityNotFoundException("Product not found");
        }
        
    }

    public void update(long id, ProductRequest productRequest) {
        
        
        try{       
        
         Product product = productRepository.getReferenceById(id);

        Category category = new Category(productRequest.getCategory().getId());
               

        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setNewProduct(productRequest.isNewProduct());
        product.setPromotion(productRequest.isPromotion());
        product.setCategory(category);

        productRepository.save(product);
        
        } catch (EntityNotFoundException  e){
            throw new EntityNotFoundException("Product not found");
        } catch (DataIntegrityViolationException e){
            throw new EntityNotFoundException("Category not found");
        }
    }

}
