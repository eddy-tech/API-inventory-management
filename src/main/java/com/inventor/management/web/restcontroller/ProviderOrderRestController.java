package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.services.interfaces.ProviderOrderService;
import com.inventor.management.web.api.ProviderOrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderOrderRestController implements ProviderOrderApi {

    private ProviderOrderService providerOrderService;

    @Autowired
    public ProviderOrderRestController(ProviderOrderService providerOrderService) {
        this.providerOrderService = providerOrderService;
    }

    @Override
    public ResponseEntity<ProviderOrderDto> saveProviderOrder(ProviderOrderDto providerOrderDto) {
        return ResponseEntity.ok(providerOrderService.saveProviderOrder(providerOrderDto));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> updateProviderOrder(ProviderOrderDto providerOrderDto) {
        return ResponseEntity.ok(providerOrderService.updateProviderOrder(providerOrderDto));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> getProviderOrder(Long id) {
        return ResponseEntity.ok(providerOrderService.getProviderOrder(id));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> getCodeProviderOrder(String codeProviderOrder) {
        return ResponseEntity.ok(providerOrderService.getCodeProviderOrder(codeProviderOrder));
    }

    @Override
    public ResponseEntity<List<ProviderOrderDto>> listProviderOrder() {
        return ResponseEntity.ok(providerOrderService.listProviderOrder());
    }

    @Override
    public ResponseEntity deleteCustomerOrder(Long id) {
        providerOrderService.deleteProviderOrder(id);
        return ResponseEntity.ok().build();
    }
}
