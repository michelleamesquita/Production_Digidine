package com.digidine.production.infrastructure.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Value("${digidine.broker.queue.production}")
    private String productionNotificationQueue;

    @Value("${digidine.broker.exchange.productionNotificationExchange}")
    private String productionNotificationExchangeName;

    @Value("${digidine.broker.key.productionNotificationKey}")
    private String productionNotificationKey;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public TopicExchange productionNotificationExchange() {
        return new TopicExchange(productionNotificationExchangeName);
    }

    @Bean
    public Queue productionNotificationQueue() {
        return new Queue(productionNotificationQueue, true);
    }

    @Bean
    public Binding productionBinding() {
        return BindingBuilder.bind(productionNotificationQueue())
                .to(productionNotificationExchange())
                .with(productionNotificationKey);
    }
}