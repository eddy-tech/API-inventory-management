package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.services.interfaces.ProviderOrderService;
import com.inventor.management.web.api.ProviderOrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
    public ResponseEntity<ProviderOrderDto> updateStateOrder(Long orderId, StateOrder stateOrder) {
        return ResponseEntity.ok(providerOrderService.updateStateOrder(orderId,stateOrder));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        return ResponseEntity.ok(providerOrderService.updateQuantityOrdered(orderId,orderLineId,quantity));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> updateProvider(Long orderId, Long customerId) {
        return ResponseEntity.ok(providerOrderService.updateProvider(orderId,customerId));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> updateArticle(Long orderId, Long orderLineId, Long articleId) {
        return ResponseEntity.ok(providerOrderService.updateArticle(orderId,orderLineId,articleId));
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
    public ResponseEntity<List<ProviderOrderLineDto>> findAllProviderOrdersLinesByProviderOrderId(Long orderId) {
        return ResponseEntity.ok(providerOrderService.findAllProviderOrdersLinesByProviderOrderId(orderId));
    }

    @Override
    public ResponseEntity<List<ProviderOrderDto>> listProviderOrder() {
        return ResponseEntity.ok(providerOrderService.listProviderOrder());
    }

    @Override
    public ResponseEntity deleteProviderOrder(Long id) {
        providerOrderService.deleteProviderOrder(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ProviderOrderDto> deleteArticle(Long orderId, Long orderLineId) {
        return ResponseEntity.ok(providerOrderService.deleteArticle(orderId,orderLineId));
    }
}
