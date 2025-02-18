package com.digidine.production.infrastructure.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductionListener {

    @RabbitListener(queues = "${digidine.broker.queue.production}")
    public void receiveOrder(OrderResponseDTO orderResponse) {
        // Aqui você processa o OrderResponseDTO recebido e dá andamento ao pedido na produção.
        System.out.println("Received order for production: " + orderResponse);

        // Implemente o processamento do pedido conforme sua regra de negócio.
    }
}
