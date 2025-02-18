package com.digidine.production.infrastructure.gateway.mapper;

import com.digidine.production.domain.entities.Product;
import com.digidine.production.infrastructure.controller.dto.ProductRequestDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public List<ProductRequestDTO> toRequests(List<Product> products){
        List<ProductRequestDTO> productRequestDTOList = new ArrayList<>();

        for (Product product : products) {
            ProductRequestDTO productRequestDTO = new ProductRequestDTO(
                    product.getProductNumber(),
                    product.getName(),
                    product.getCategory()
            );
            productRequestDTOList.add(productRequestDTO);
        }
        return productRequestDTOList;
    }

}