package com.digidine.production.infrastructure.configuration;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.application.usecase.ProcessOrderUseCase;
import com.digidine.production.application.usecase.UpdateOrderStatusUseCase;
import com.digidine.production.infrastructure.gateway.OrderRepositoryGateway;
import com.digidine.production.infrastructure.gateway.mapper.OrderEntityMapper;
import com.digidine.production.infrastructure.gateway.mapper.OrderMapper;
import com.digidine.production.infrastructure.gateway.mapper.ProductEntityMapper;
import com.digidine.production.infrastructure.gateway.mapper.ProductMapper;
import com.digidine.production.infrastructure.persistence.OrderOracleDBRepository;
import com.digidine.production.infrastructure.producer.OrderUpdateProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    OrderGateway orderGateway(OrderEntityMapper mapper, OrderOracleDBRepository repository, OrderUpdateProducer producer) {
        return new OrderRepositoryGateway(repository, mapper, producer);
    }

    @Bean
    UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderGateway orderGateway){
        return new UpdateOrderStatusUseCase(orderGateway);
    }

    @Bean
    ProcessOrderUseCase processOrderUseCase(OrderGateway orderGateway){
        return new ProcessOrderUseCase(orderGateway);
    }

    @Bean
    OrderEntityMapper orderEntityMapper(ProductEntityMapper productEntityMapper) {
        return new OrderEntityMapper(productEntityMapper);
    }

    @Bean
    ProductEntityMapper productEntityMapper() {
        return new ProductEntityMapper();
    }

    @Bean
    ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    OrderMapper orderMapper(ProductMapper productMapper) {
        return new OrderMapper(productMapper);
    }

}
