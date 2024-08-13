package com.inventor.management.inventor_management.provider.resource.restcontroller;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.dto.ProviderRequest;
import com.inventor.management.inventor_management.provider.service.ProviderService;
import com.inventor.management.inventor_management.provider.resource.api.ProviderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.provider.roots.ProviderEndPoint.PROVIDER_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROVIDER_ENDPOINT)
public class ProviderRestController implements ProviderApi {
    private final ProviderService providerService;

    @Override
    public ResponseEntity<ProviderDto> saveProvider(ProviderRequest providerRequest) {
        return ResponseEntity.status(CREATED)
                .body(providerService.saveProvider(providerRequest));
    }

    @Override
    public ResponseEntity<ProviderDto> updateProvider(Long providerId, ProviderRequest providerRequest) {
        return ResponseEntity.ok(providerService.updateProvider(providerRequest, providerId));
    }

    @Override
    public ResponseEntity<ProviderDto> getProvider(Long id) {
        return ResponseEntity.ok(providerService.getProvider(id));
    }

    @Override
    public ResponseEntity<List<ProviderDto>> listProviders() {
        return ResponseEntity.ok(providerService.listProvider());
    }

    @Override
    public ResponseEntity<?> deleteProvider(Long id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }
}
