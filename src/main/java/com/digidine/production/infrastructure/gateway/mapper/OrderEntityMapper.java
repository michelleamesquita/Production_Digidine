package com.digidine.production.infrastructure.gateway.mapper;

import com.digidine.production.domain.entities.Order;
import com.digidine.production.infrastructure.persistence.entitie.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderEntityMapper {

    private ProductEntityMapper productEntityMapper;

    public OrderEntityMapper(ProductEntityMapper productEntityMapper){
        this.productEntityMapper = productEntityMapper;
    }

    public OrderEntity toEntity(Order order){

        return new OrderEntity(order.getOrderNumber(), productEntityMapper.toEntities(order.getProducts()), order.getOrderStatus(), order.getCreatedAt());
    }

    public Order toDomain(OrderEntity entity){
        return new Order(entity.getOrderNumber(), productEntityMapper.toDomains(entity.getProducts()), entity.getOrderStatus(), entity.getCreatedAt());
    }

    public Optional<Order> toOptionalDomain(Optional<OrderEntity> optionalEntity) {
        return optionalEntity.map(this::toDomain);
    }

    public List<Order> toDomains(List<OrderEntity> entities){
        List<Order> orders = new ArrayList<>();

        for (OrderEntity entity : entities) {
            Order order = new Order(
                    entity.getOrderNumber(),
                    productEntityMapper.toDomains(entity.getProducts()),
                    entity.getOrderStatus(),
                    entity.getCreatedAt()
            );
            orders.add(order);
        }
        return orders;
    }
}
