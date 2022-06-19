package com.inventor.management.repository;

import com.inventor.management.entities.SaleLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleLineRepository extends JpaRepository<SaleLine, Long> {
}