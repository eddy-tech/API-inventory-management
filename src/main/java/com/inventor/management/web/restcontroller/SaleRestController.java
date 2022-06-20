package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.SaleDto;
import com.inventor.management.services.interfaces.SaleService;
import com.inventor.management.web.api.SaleApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SaleRestController implements SaleApi {

    private SaleService saleService;

    @Override
    public SaleDto saveSale(SaleDto saleDto) {
        return saleService.saveSale(saleDto);
    }

    @Override
    public SaleDto updateSale(SaleDto saleDto) {
        return saleService.updateSale(saleDto);
    }

    @Override
    public SaleDto getSale(Long id) {
        return saleService.getSale(id);
    }

    @Override
    public SaleDto getCodeSale(String codeSale) {
        return saleService.getCodeSale(codeSale);
    }

    @Override
    public List<SaleDto> listSale() {
        return saleService.listSale();
    }

    @Override
    public void deleteSale(Long id) {
        saleService.deleteSale(id);
    }
}
