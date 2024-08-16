package com.inventor.management.inventor_management.enterprise.service;

import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;

import java.util.List;

public interface EnterpriseService {
    EnterpriseDto saveEnterprise (EnterpriseDto enterpriseDto);
    EnterpriseDto updateEnterprise (EnterpriseDto enterpriseDto, Long id);
    EnterpriseDto getEnterprise (Long id);
    Enterprise findById(Long id);
    List<EnterpriseDto> listEnterprise ();
    void deleteEnterprise (Long id);
}
