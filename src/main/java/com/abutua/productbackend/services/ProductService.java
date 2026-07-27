package com.abutua.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.productbackend.dtos.ProductRequest;
import com.abutua.productbackend.dtos.ProductResponse;
import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;
import com.abutua.productbackend.repositories.CategoryRepository;
import com.abutua.productbackend.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return product;
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(Product::toDto).collect(java.util.stream.Collectors.toList());
    }

    public ProductResponse save(ProductRequest productRequest) {
        Product newProduct = productRepository.save(productRequest.toEntity());
        return newProduct.toDto();
    }

    public ProductResponse getDTOById(long id) {
        Product product = getById(id);
        return product.toDto();
    }

    public void deleteById(long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public void update(long id, ProductRequest productRequest) {
        Product product = getById(id);

        if (productRequest.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category can not be empty");
        }

        Category category = categoryService.getById(productRequest.getCategory().getId());

        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setNewProduct(productRequest.isNewProduct());
        product.setPromotion(productRequest.isPromotion());
        product.setCategory(new Category(category.getId()));

        productRepository.save(product);
    }

}
