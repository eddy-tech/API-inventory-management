package com.inventor.management.inventor_management.provider.resource.restcontroller;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.service.ProviderService;
import com.inventor.management.inventor_management.provider.resource.api.ProviderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.provider.roots.ProviderEndPoint.PROVIDER_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROVIDER_ENDPOINT)
public class ProviderRestController implements ProviderApi {
    private final ProviderService providerService;

    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        return providerService.saveProvider(providerDto);
    }

    @Override
    public ProviderDto updateProvider(Long providerId, ProviderDto providerDto) {
        return providerService.updateProvider(providerDto);
    }

    @Override
    public ProviderDto getProvider(Long id) {
        return providerService.getProvider(id);
    }

    @Override
    public List<ProviderDto> listProviders() {
        return providerService.listProvider();
    }

    @Override
    public void deleteProvider(Long id) {
         providerService.deleteProvider(id);
    }
}
