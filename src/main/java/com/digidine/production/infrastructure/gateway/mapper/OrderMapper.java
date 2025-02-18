package com.digidine.production.infrastructure.gateway.mapper;

import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.Product;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;
import com.digidine.production.infrastructure.controller.dto.ProductRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }

    public OrderRequest toRequest(Order order){
        return new OrderRequest(
                order.getOrderNumber(),
                productMapper.toRequests(order.getProducts()),
                order.getOrderStatus().toString(),
                order.getCreatedAt()
        );
    }
}
