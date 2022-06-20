package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.EnterpriseDto;
import com.inventor.management.services.interfaces.EnterpriseService;
import com.inventor.management.web.api.EnterpriseApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EnterpriseRestController implements EnterpriseApi {

    private EnterpriseService enterpriseService;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        return enterpriseService.saveEnterprise(enterpriseDto);
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
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
