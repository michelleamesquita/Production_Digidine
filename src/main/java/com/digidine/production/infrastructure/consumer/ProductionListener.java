package com.digidine.production.infrastructure.consumer;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.infrastructure.controller.dto.OrderDTO;
import com.digidine.production.infrastructure.gateway.mapper.OrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductionListener {

    @Autowired
    private OrderGateway orderGateway;

    @Autowired
    private OrderMapper orderMapper;

    @RabbitListener(queues = "${digidine.broker.queue.production}")
    public void receiveOrder(OrderDTO orderDTO) {
        orderGateway.processOrder(orderMapper.toRequest(orderDTO));
    }
}
