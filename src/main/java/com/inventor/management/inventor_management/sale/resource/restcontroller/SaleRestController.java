package com.inventor.management.inventor_management.sale.resource.restcontroller;

import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.sale.dto.SaleRequest;
import com.inventor.management.inventor_management.sale.service.SaleService;
import com.inventor.management.inventor_management.sale.resource.api.SaleApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.sale.roots.SaleEndPoint.SALE_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(SALE_ENDPOINT)
public class SaleRestController implements SaleApi {
    private final SaleService saleService;

    @Override
    public ResponseEntity<SaleDto> saveSale(SaleRequest saleRequest) {
        return ResponseEntity.status(CREATED)
                .body(saleService.saveSale(saleRequest));
    }
    @Override
    public ResponseEntity<SaleDto> updateSale(Long saleId, SaleRequest saleRequest) {
        return ResponseEntity.ok(saleService.updateSale(saleRequest, saleId));
    }
    @Override
    public ResponseEntity<SaleDto> getSale(Long id) {
        return ResponseEntity.ok(saleService.getSale(id));
    }
    @Override
    public ResponseEntity<SaleDto> getCodeSale(String codeSale) {
        return ResponseEntity.ok(saleService.getCodeSale(codeSale));
    }
    @Override
    public ResponseEntity<List<SaleDto>> listSale() {
        return ResponseEntity.ok(saleService.listSale());
    }
    @Override
    public ResponseEntity<?> deleteSale(Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
