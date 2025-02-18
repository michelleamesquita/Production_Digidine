package com.digidine.production.infrastructure.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(Long orderNumber,
                           List<ProductRequestDTO> products,
                           String orderStatus,
                           LocalDateTime createdAt){}
