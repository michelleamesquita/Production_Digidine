package com.digidine.production.infrastructure.persistence;

import com.digidine.production.infrastructure.persistence.entitie.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOracleDBRepository extends JpaRepository<ProductEntity, Long> {

}
