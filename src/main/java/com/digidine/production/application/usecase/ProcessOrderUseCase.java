package com.digidine.production.application.usecase;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.domain.entities.Order;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;

public class ProcessOrderUseCase {

    private final OrderGateway ordersGateway;

    public ProcessOrderUseCase(OrderGateway ordersGateway) {
        this.ordersGateway = ordersGateway;
    }

    public Order processOrder(OrderRequest order) {
        return ordersGateway.processOrder(order);
    }
}
