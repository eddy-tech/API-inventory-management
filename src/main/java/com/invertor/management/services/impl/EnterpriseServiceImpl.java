package com.invertor.management.services.impl;

import com.invertor.management.dto.EnterpriseDto;
import com.invertor.management.entities.Enterprise;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.EnterpriseRepository;
import com.invertor.management.services.interfaces.EnterpriseService;
import com.invertor.management.validators.EnterpriseValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {

    private EnterpriseRepository enterpriseRepository;
    private StockMapperImpl dtoMapper;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid",enterpriseDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }

        Enterprise enterprise = dtoMapper.fromEnterpriseDto(enterpriseDto);
        Enterprise savedEnterprise = enterpriseRepository.save(enterprise);
        return dtoMapper.fromEnterprise(savedEnterprise);
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid",enterpriseDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }

        Enterprise enterprise = dtoMapper.fromEnterpriseDto(enterpriseDto);
        Enterprise savedEnterprise = enterpriseRepository.save(enterprise);
        return dtoMapper.fromEnterprise(savedEnterprise);
    }

    @Override
    public EnterpriseDto getEnterprise(Long id) {
        if(id == null) {
            log.error("Enterprise ID is null");
            return null;
        }

        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing Enterprise with ID ="+ id + "was found in DataBase",ErrorCodes.ENTERPRISE_NOT_FOUND));
        return dtoMapper.fromEnterprise(enterprise);
    }

    @Override
    public List<EnterpriseDto> listEnterprise() {
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        List<EnterpriseDto> enterpriseDtoList = enterpriseList.stream()
                .map(enterprise -> dtoMapper.fromEnterprise(enterprise)).collect(Collectors.toList());

        return enterpriseDtoList;
    }

    @Override
    public void deleteEnterprise(Long id) {
        if(id == null){
            log.error("id is invalid");
            return;
        }

        enterpriseRepository.deleteById(id);
    }
}
