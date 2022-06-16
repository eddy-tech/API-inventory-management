package com.invertor.management.services.interfaces;

import com.invertor.management.dto.ProviderOrderDto;

import java.util.List;

public interface ProviderOrderService {

    ProviderOrderDto saveProviderOrder (ProviderOrderDto providerOrderDto);

    ProviderOrderDto updateProviderOrder (ProviderOrderDto providerOrderDto);

    ProviderOrderDto getProviderOrder (Long id);

    ProviderOrderDto getCodeProviderOrder (String codeProviderOrder);

    List<ProviderOrderDto> listProviderOrder ();

    public void deleteProviderOrder (Long id);
}
