package com.digidine.production.application.gateway;

import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.enums.OrderStatus;

public interface OrderGateway {
    Order updateOrderStatusByOrderNumber(Long orderNumber, OrderStatus newStatus);
    Order processOrder(Order order);
}