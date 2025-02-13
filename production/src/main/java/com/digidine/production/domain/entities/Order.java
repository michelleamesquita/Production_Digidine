package com.digidine.production.domain.entities;

import com.digidine.production.domain.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private Long orderNumber;

    private List<Product> products;

    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    public Order() {}

    public Order(Long orderNumber, List<Product> products, OrderStatus orderStatus, LocalDateTime createdAt) {
        this.orderNumber = orderNumber;
        this.products = products;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        if (products != null) {
            for (Product product : products) {
                product.setOrder(this);
            }
        }
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
