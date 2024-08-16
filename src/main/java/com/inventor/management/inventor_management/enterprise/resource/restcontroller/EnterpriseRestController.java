package com.inventor.management.inventor_management.enterprise.resource.restcontroller;

import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.service.EnterpriseService;
import com.inventor.management.inventor_management.enterprise.resource.api.EnterpriseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.enterprise.roots.EnterpriseEndPoint.ENTERPRISE_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(ENTERPRISE_ENDPOINT)
public class EnterpriseRestController implements EnterpriseApi {
    private final EnterpriseService enterpriseService;

    @Override
    public ResponseEntity<EnterpriseDto> saveEnterprise(EnterpriseDto enterpriseDto) {
        return ResponseEntity.status(CREATED)
                .body(enterpriseService.saveEnterprise(enterpriseDto));
    }

    @Override
    public ResponseEntity<EnterpriseDto> updateEnterprise(EnterpriseDto enterpriseDto, Long enterpriseId) {
        return ResponseEntity.ok(enterpriseService.updateEnterprise(enterpriseDto, enterpriseId));
    }

    @Override
    public ResponseEntity<EnterpriseDto> getEnterprise(Long id) {
        return ResponseEntity.ok(enterpriseService.getEnterprise(id));
    }

    @Override
    public ResponseEntity<List<EnterpriseDto>> listEnterprise() {
        return ResponseEntity.ok(enterpriseService.listEnterprise());
    }

    @Override
    public ResponseEntity<?> deleteEnterprise(Long id) {
        enterpriseService.deleteEnterprise(id);
        return ResponseEntity.noContent().build();
    }
}
