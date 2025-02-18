package com.digidine.production.infrastructure.controller;

import com.digidine.production.application.gateway.OrderGateway;
import com.digidine.production.domain.entities.Order;
import com.digidine.production.domain.entities.enums.OrderStatus;
import com.digidine.production.infrastructure.controller.dto.OrderRequest;
import com.digidine.production.infrastructure.controller.dto.OrderStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/production")
public class ProductionController {

    private final OrderGateway orderGateway;

    public ProductionController(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @PutMapping("/{orderNumber}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderNumber, @RequestBody OrderStatusRequest request ) {
        OrderStatus newStatus = OrderStatus.valueOf(request.orderStatus());
        Order updatedOrder = orderGateway.updateOrderStatusByOrderNumber(orderNumber, newStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @PostMapping()
    public ResponseEntity<Order> processOrder(@RequestBody OrderRequest request ) {
        Order order = orderGateway.processOrder(request);
        return ResponseEntity.ok(order);
    }
}
