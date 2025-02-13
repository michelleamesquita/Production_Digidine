package com.digidine.production.infrastructure.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
    @Value("${broker.exchange.orderNotificationExchange}")
    private String orderNotificationExchange;

    @Value("${broker.key.orderNotificationKey}")
    private String orderNotificationKey;

    @Value("${broker.key.order.queue}")
    private String orderNotificationQueue;

    @Bean
    public Queue orderNotificationQueue() {
        return new Queue(orderNotificationQueue, true);
    }

    @Bean
    public DirectExchange orderNotificationExchange() {
        return new DirectExchange(orderNotificationExchange);
    }

    @Bean
    public Binding bindingOrderNotification(Queue orderNotificationQueue, DirectExchange orderNotificationExchange) {
        return BindingBuilder.bind(orderNotificationQueue).to(orderNotificationExchange).with(orderNotificationKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
