package com.inventor.management.inventor_management.providerOrder.service;

import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderRequest;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;

import java.math.BigDecimal;
import java.util.List;

public interface ProviderOrderService {
    ProviderOrderDto saveProviderOrder (ProviderOrderRequest providerOrderRequest);
    ProviderOrderDto updateProviderOrder (ProviderOrderRequest providerOrderRequest, Long id);
    ProviderOrderDto updateStateOrder (Long orderId, StateOrder stateOrder);
    ProviderOrderDto updateQuantityOrdered (Long orderId, Long orderLineId, BigDecimal quantity);
    ProviderOrderDto updateProvider (Long orderId, Long providerId);
    ProviderOrderDto updateArticle (Long orderId, Long orderLineId, Long articleId);
    ProviderOrderDto getProviderOrder (Long id);
    ProviderOrderDto getCodeProviderOrder (String codeProviderOrder);
    List<ProviderOrderDto> listProviderOrder ();
    List<ProviderOrderLineDto> findAllProviderOrdersLinesByProviderOrderId (Long orderId);
    void deleteProviderOrder (Long id);
    // DELETE PROVIDER ORDER LINE
    ProviderOrderDto deleteArticle (Long orderId, Long orderLineId);
}
