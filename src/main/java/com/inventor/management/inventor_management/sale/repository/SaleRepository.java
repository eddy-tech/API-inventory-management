package com.inventor.management.inventor_management.sale.repository;

import com.inventor.management.inventor_management.sale.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    Sale findByCodeSale (String codeSale);
}