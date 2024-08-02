package com.inventor.management.enterprise.resource.restcontroller;

import com.inventor.management.enterprise.dto.EnterpriseDto;
import com.inventor.management.enterprise.service.EnterpriseService;
import com.inventor.management.enterprise.resource.api.EnterpriseApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.enterprise.roots.EnterpriseEndPoint.ENTERPRISE_ENDPOINT;

@RestController
@AllArgsConstructor
@RequestMapping(ENTERPRISE_ENDPOINT)
public class EnterpriseRestController implements EnterpriseApi {

    private final EnterpriseService enterpriseService;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        return enterpriseService.saveEnterprise(enterpriseDto);
    }

    @Override
    public EnterpriseDto updateEnterprise(Long enterpriseId, EnterpriseDto enterpriseDto) {
        return enterpriseService.updateEnterprise(enterpriseDto);
    }

    @Override
    public EnterpriseDto getEnterprise(Long id) {
        return enterpriseService.getEnterprise(id);
    }

    @Override
    public List<EnterpriseDto> listEnterprise() {
        return enterpriseService.listEnterprise();
    }

    @Override
    public void deleteEnterprise(Long id) {
       enterpriseService.deleteEnterprise(id);
    }
}
