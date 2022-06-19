package com.inventor.management.services.interfaces;

import com.inventor.management.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseService {

    EnterpriseDto saveEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto updateEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto getEnterprise (Long id);

    List<EnterpriseDto> listEnterprise ();

    void deleteEnterprise (Long id);
}
