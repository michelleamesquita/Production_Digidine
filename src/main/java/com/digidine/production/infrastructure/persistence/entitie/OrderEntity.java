package com.digidine.production.infrastructure.persistence.entitie;

import com.digidine.production.domain.entities.Product;
import com.digidine.production.domain.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private Long orderNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    public OrderEntity() {}

    public OrderEntity(Long orderNumber, List<ProductEntity> products, OrderStatus orderStatus, LocalDateTime createdAt) {
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
        if (this.products != null) {
            for (ProductEntity product : this.products) {
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
