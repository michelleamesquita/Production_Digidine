package com.digidine.production.domain.entities;

import com.digidine.production.domain.entities.enums.ProductCategory;

public class Product {
    private Long productNumber;

    private String name;

    private Double price;

    private ProductCategory category;

    private Order order;

    public Product() {}

    public Product(Long productNumber, String name, Double price, ProductCategory category) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Long productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
