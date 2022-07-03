package com.inventor.management.services.interfaces;

import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.enums.StateOrder;

import java.math.BigDecimal;
import java.util.List;

public interface ProviderOrderService {

    ProviderOrderDto saveProviderOrder (ProviderOrderDto providerOrderDto);

    ProviderOrderDto updateProviderOrder (ProviderOrderDto providerOrderDto);

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
