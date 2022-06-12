package com.invertor.management.repository;

import com.invertor.management.entities.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}