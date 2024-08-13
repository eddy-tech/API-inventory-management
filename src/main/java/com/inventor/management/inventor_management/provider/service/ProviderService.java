package com.inventor.management.inventor_management.provider.service;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.dto.ProviderRequest;

import java.util.List;

public interface ProviderService {
    ProviderDto saveProvider (ProviderRequest providerRequest);
    ProviderDto updateProvider (ProviderRequest providerRequest, Long id);
    ProviderDto getProvider (Long id);
    List<ProviderDto> listProvider ();
    void deleteProvider (Long id);
}
