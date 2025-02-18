package com.digidine.production.infrastructure.controller.dto;

import com.digidine.production.domain.entities.enums.ProductCategory;

public record ProductRequestDTO(
        Long productNumber,
        String name,
        Double price,
        ProductCategory category
) {}
