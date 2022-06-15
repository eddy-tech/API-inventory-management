package com.invertor.management.services.interfaces;

import com.invertor.management.dto.CustomerDto;
import com.invertor.management.dto.EnterpriseDto;

import java.util.List;

public interface EnterpriseService {

    EnterpriseDto saveEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto updateEnterprise (EnterpriseDto enterpriseDto);

    EnterpriseDto getEnterprise (Long id);

    List<EnterpriseDto> listEnterprise ();

    void deleteEnterprise (Long id);
}
