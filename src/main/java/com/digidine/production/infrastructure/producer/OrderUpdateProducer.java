package com.digidine.production.infrastructure.producer;

import com.digidine.production.infrastructure.controller.dto.OrderDTO;
import com.digidine.production.infrastructure.controller.dto.OrderResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${digidine.broker.exchange.orderUpdateExchange}")
    private String orderUpdateExchange;

    @Value("${digidine.broker.key.orderUpdateKey}")
    private String orderUpdateKey;

    public void sendOrderUpdate(OrderResponse orderResponse) {
        rabbitTemplate.convertAndSend(orderUpdateExchange, orderUpdateKey, orderResponse);
    }
}
