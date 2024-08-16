package com.inventor.management.inventor_management.enterprise.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.inventor_management.enterprise.repository.EnterpriseRepository;
import com.inventor.management.inventor_management.enterprise.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final EnterpriseMapper enterpriseMapper;
    private final ObjectValidator validator;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        validator.validate(enterpriseDto);

        var enterprise = enterpriseMapper.fromEnterpriseDto(enterpriseDto);
        var savedEnterprise = enterpriseRepository.save(enterprise);
        return enterpriseMapper.fromEnterprise(savedEnterprise);
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto, Long id) {
        validator.validate(enterpriseDto);
        var enterprise = this.getEnterprise(id);

        enterprise.setName(enterpriseDto.getName());
        enterprise.setDescription(enterpriseDto.getDescription());
        enterprise.setMail(enterpriseDto.getMail());
        enterprise.setPicture(enterpriseDto.getPicture());
        enterprise.setNumTel(enterpriseDto.getNumTel());
        enterprise.setAddressDto(enterpriseDto.getAddressDto());
        enterprise.setCodeFiscal(enterpriseDto.getCodeFiscal());
        enterprise.setSiteWeb(enterpriseDto.getSiteWeb());

        return enterpriseMapper.fromEnterprise(
                enterpriseRepository.save(
                        enterpriseMapper.fromEnterpriseDto(enterprise)
                )
        );
    }

    @Override
    public EnterpriseDto getEnterprise(Long id) {
        if(id == null) {
            log.error("Enterprise ID is null");
            return null;
        }
        var enterprise = this.findById(id);

        return enterpriseMapper.fromEnterprise(enterprise);
    }

    @Override
    public Enterprise findById(Long id) {
        return enterpriseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing Enterprise with ID ="+ id + "was found in DataBase"
                        )
                );
    }

    @Override
    public List<EnterpriseDto> listEnterprise() {
        return enterpriseRepository.findAll().stream()
                .map(enterpriseMapper::fromEnterprise)
                .toList();
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
