package com.inventor.management.inventor_management.providerOrder.resource.restcontroller;

import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrder.resource.api.ProviderOrderApi;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.inventor_management.providerOrder.service.ProviderOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.inventor.management.inventor_management.providerOrder.roots.ProviderOrderEndPoint.PROVIDER_ORDER_ENDPOINT;

@RestController
@RequestMapping(PROVIDER_ORDER_ENDPOINT)
@RequiredArgsConstructor
public class ProviderOrderRestController implements ProviderOrderApi {
    private final ProviderOrderService providerOrderService;

    @Override
    public ResponseEntity<ProviderOrderDto> saveProviderOrder(ProviderOrderDto providerOrderDto) {
        return ResponseEntity.ok(providerOrderService.saveProviderOrder(providerOrderDto));
    }

    @Override
    public ResponseEntity<ProviderOrderDto> updateProviderOrder(Long providerOrderId, ProviderOrderDto providerOrderDto) {
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
    public ResponseEntity<?> deleteProviderOrder(Long id) {
        providerOrderService.deleteProviderOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> deleteArticle(Long orderId, Long orderLineId) {
        providerOrderService.deleteArticle(orderId,orderLineId);
        return ResponseEntity.noContent().build();
    }
}
