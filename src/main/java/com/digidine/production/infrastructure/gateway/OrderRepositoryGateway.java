package com.digidine.production.infrastructure.gateway;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.enums.OrderStatus;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;
import com.digidine.production.infrastructure.gateway.mapper.OrderEntityMapper;
import com.digidine.production.infrastructure.persistence.OrderOracleDBRepository;
import com.digidine.production.infrastructure.persistence.entitie.OrderEntity;
import com.digidine.production.infrastructure.producer.OrderUpdateProducer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OrderRepositoryGateway implements OrderGateway {

    private final OrderOracleDBRepository repository;
    private final OrderEntityMapper mapper;
    private final OrderUpdateProducer orderUpdateProducer;

    public OrderRepositoryGateway(OrderOracleDBRepository repository, OrderEntityMapper mapper, OrderUpdateProducer orderUpdateProducer)
    {
        this.repository = repository;
        this.mapper = mapper;
        this.orderUpdateProducer = orderUpdateProducer;
    }

    @Override
    public Order updateOrderStatusByOrderNumber(Long orderNumber, OrderStatus newStatus) {
        OrderEntity orderEntity = repository.findByOrderNumber(orderNumber);

        if(orderEntity == null )
        {
            throw new IllegalArgumentException();
        }
        orderEntity.setOrderStatus(newStatus);

        repository.save(orderEntity);

        try {
            orderUpdateProducer.sendOrderUpdate(mapper.toDomain(orderEntity));
        }catch (Exception e) {
            log.warn("Error sending notification!");
        }

        return mapper.toDomain(orderEntity);
    }

    @Override
    public Order processOrder(OrderRequest order) {
        OrderEntity orderEntity = repository.findByOrderNumber(order.orderNumber());

        if(orderEntity == null )
        {
            throw new IllegalArgumentException();
        }

        orderEntity.setOrderStatus(OrderStatus.EM_PREPARACAO);

        repository.save(orderEntity);

        try {
            orderUpdateProducer.sendOrderUpdate(mapper.toDomain(orderEntity));
        }catch (Exception e) {
            log.warn("Error sending notification!");
        }

        return mapper.toDomain(orderEntity);
    }
}
