package com.inventor.management.inventor_management.provider.service;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;

import java.util.List;

public interface ProviderService {
    ProviderDto saveProvider (ProviderDto providerDto);
    ProviderDto updateProvider (ProviderDto providerDto);
    ProviderDto getProvider (Long id);
    List<ProviderDto> listProvider ();
    void deleteProvider (Long id);
}
