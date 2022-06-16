package com.invertor.management.services.interfaces;

import com.invertor.management.dto.SaleDto;

import java.util.List;

public interface SaleService {

    SaleDto saveSale (SaleDto saleDto);

    SaleDto updateSale (SaleDto saleDto);

    SaleDto getSale (Long id);

    SaleDto getCodeSale (String codeSale);

    List<SaleDto> listSale ();

    void deleteSale (Long id);
}
