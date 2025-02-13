package com.digidine.production.infrastructure.consumer;

import com.digidine.production.application.usecase.ProcessOrderUseCase;
import com.digidine.production.domain.entities.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderNotificationListener {
    private final ProcessOrderUseCase processOrderUseCase;
    private final ObjectMapper objectMapper;

    public OrderNotificationListener(ProcessOrderUseCase processOrderUseCase, ObjectMapper objectMapper) {
        this.processOrderUseCase = processOrderUseCase;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${broker.key.order.queue}")
    public void receiveMessage(String message) throws IOException {
        Order order = objectMapper.readValue(message, Order.class);
        processOrderUseCase.processOrder(order);
    }
}
