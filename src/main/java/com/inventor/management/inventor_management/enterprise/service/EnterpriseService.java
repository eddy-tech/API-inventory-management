package com.inventor.management.inventor_management.enterprise.service;

import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseService {

    EnterpriseDto saveEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto updateEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto getEnterprise (Long id);

    List<EnterpriseDto> listEnterprise ();

    void deleteEnterprise (Long id);
}
