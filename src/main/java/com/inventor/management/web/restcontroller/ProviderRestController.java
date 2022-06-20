package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.ProviderDto;
import com.inventor.management.services.interfaces.ProviderService;
import com.inventor.management.web.api.ProviderApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProviderRestController implements ProviderApi {

    private ProviderService providerService;

    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        return providerService.saveProvider(providerDto);
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto) {
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
