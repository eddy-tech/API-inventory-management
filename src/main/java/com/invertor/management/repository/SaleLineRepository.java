package com.invertor.management.repository;

import com.invertor.management.entities.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleLineRepository extends JpaRepository<SaleLine, Long> {
}