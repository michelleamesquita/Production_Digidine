package com.digidine.production.infrastructure.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long orderNumber,
        CustomerRequestDTO customer,
        List<ProductRequestDTO> products,
        double totalPrice,
        String orderStatus,
        LocalDateTime createdAt){}
