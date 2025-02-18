package com.digidine.production.infrastructure.consumer;

import com.digidine.production.application.usecase.ProcessOrderUseCase;
import com.digidine.production.domain.entities.Order;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;
import com.digidine.production.infrastructure.gateway.mapper.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderNotificationListener {
    private final ProcessOrderUseCase processOrderUseCase;
    private final ObjectMapper objectMapper;
    private final ProductMapper productMapper;

    public OrderNotificationListener(ProcessOrderUseCase processOrderUseCase, ObjectMapper objectMapper, ProductMapper mapper) {
        this.processOrderUseCase = processOrderUseCase;
        this.objectMapper = objectMapper;
        this.productMapper = mapper;
    }

    @RabbitListener(queues = "${broker.key.order.queue}")
    public void receiveMessage(String message) throws IOException {
        Order order = objectMapper.readValue(message, Order.class);
        OrderRequest orderRequest = new OrderRequest(
                order.getOrderNumber(),
                productMapper.toRequests(order.getProducts()),
                order.getOrderStatus().toString(),
                order.getCreatedAt());

        processOrderUseCase.processOrder(orderRequest);
    }
}
