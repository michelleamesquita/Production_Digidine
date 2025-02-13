package com.digidine.production.infrastructure.producer;

import com.digidine.production.domain.entities.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${broker.exchange.productionNotificationExchange}")
    private String productionNotificationExchange;

    @Value("${broker.key.productionNotificationKey}")
    private String productionNotificationKey;

    public OrderUpdateProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrderUpdate(Order order) {
        try {
            String message = objectMapper.writeValueAsString(order);
            rabbitTemplate.convertAndSend(productionNotificationExchange, productionNotificationKey, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
