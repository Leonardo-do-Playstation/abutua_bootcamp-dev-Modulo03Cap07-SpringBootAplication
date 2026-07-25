package com.abutua.productbackend.dtos;
import com.abutua.productbackend.models.Category;

public class ProductResponse {
    
    private Long id;

    private String name;
    private String description;

    private CategoryResponse category;

    private boolean promotion;
    private boolean newProduct;

    private double price;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, String description, CategoryResponse category, boolean promotion,
            boolean newProduct, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.promotion = promotion;
        this.newProduct = newProduct;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
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

    


}
