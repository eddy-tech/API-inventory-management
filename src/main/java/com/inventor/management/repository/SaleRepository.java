package com.inventor.management.repository;

import com.inventor.management.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Sale findSaleByCodeSale (String codeSale);
}