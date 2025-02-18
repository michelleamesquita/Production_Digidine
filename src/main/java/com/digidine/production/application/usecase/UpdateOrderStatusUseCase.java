package com.digidine.production.application.usecase;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.enums.OrderStatus;

public class UpdateOrderStatusUseCase {

    private final OrderGateway ordersGateway;

    public UpdateOrderStatusUseCase(OrderGateway ordersGateway) {
        this.ordersGateway = ordersGateway;
    }

    public Order updateOrderStatusByOrderNumber(long orderNumber, OrderStatus orderStatus) {
        return ordersGateway.updateOrderStatusByOrderNumber(orderNumber, orderStatus);
    }

}
