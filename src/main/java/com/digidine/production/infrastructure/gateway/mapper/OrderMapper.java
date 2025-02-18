package com.digidine.production.infrastructure.gateway.mapper;

import com.digidine.production.domain.entities.Order;
import com.digidine.production.infrastructure.controller.dto.OrderDTO;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;

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

    public OrderRequest toRequest(OrderDTO orderDTO)
    {
        return new OrderRequest(
                orderDTO.orderNumber(),
                orderDTO.products(),
                orderDTO.orderStatus(),
                orderDTO.createdAt()
        );
    }
}
