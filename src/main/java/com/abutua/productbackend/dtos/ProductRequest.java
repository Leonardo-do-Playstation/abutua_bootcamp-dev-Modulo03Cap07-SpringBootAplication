package com.abutua.productbackend.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.abutua.productbackend.models.Category;
import com.abutua.productbackend.models.Product;

public class ProductRequest {

    private Long id;


    @NotBlank(message = "Name can't be null")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    private String name;


    @NotBlank(message = "Description can't be null")
    @Size(min = 3, max = 1024, message = "Description must be between 3 and 1024 characters")
    private String description;

    private IntegerDTO category;

    private boolean promotion;
    private boolean newProduct;

    @Min(value = 0, message = "Value must be greater than 0")
    private double price;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IntegerDTO getCategory() {
        return category;
    }


    public void setCategory(IntegerDTO category) {
        this.category = category;
    }


    public Product toEntity(){
        Product product = new Product();
        
        product.setName(name);
        product.setDescription(description);
        product.setCategory(new Category(category.getId()));
        product.setPromotion(promotion);
        product.setNewProduct(newProduct);
        product.setPrice(price);

        return product;
    }

}