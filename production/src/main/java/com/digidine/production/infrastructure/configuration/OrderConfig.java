package com.digidine.production.infrastructure.configuration;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.application.usecase.ProcessOrderUseCase;
import com.digidine.production.application.usecase.UpdateOrderStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderGateway orderGateway){
        return new UpdateOrderStatusUseCase(orderGateway);
    }

    @Bean
    ProcessOrderUseCase processOrderUseCase(OrderGateway orderGateway){
        return new ProcessOrderUseCase(orderGateway);
    }
}
