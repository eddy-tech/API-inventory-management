package com.inventor.management.inventor_management.sale.service;

import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.sale.dto.SaleRequest;

import java.util.List;

public interface SaleService {
    SaleDto saveSale (SaleRequest saleRequest);
    SaleDto updateSale (SaleRequest saleRequest, Long id);
    SaleDto getSale (Long id);
    SaleDto getCodeSale (String codeSale);
    List<SaleDto> listSale ();
    void deleteSale (Long id);
}
