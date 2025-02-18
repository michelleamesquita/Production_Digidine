package com.digidine.production.infrastructure.persistence;

import com.digidine.production.infrastructure.persistence.entitie.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOracleDBRepository extends JpaRepository<OrderEntity, Long> {

    OrderEntity findByOrderNumber(long orderNumber);
}
