package com.digidine.production.infrastructure.persistence.entitie;

import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private Long productNumber;

    private String name;

    private Double price;

    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "order_number")
    private OrderEntity order;

    public ProductEntity() {}

    public ProductEntity(Long productNumber, String name, Double price, ProductCategory category) {
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
