package com.inventor.management.services.impl;

import com.inventor.management.dto.RolesDto;
import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.Enterprise;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.EnterpriseDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.EnterpriseRepository;
import com.inventor.management.repository.RolesRepository;
import com.inventor.management.services.interfaces.EnterpriseService;
import com.inventor.management.services.interfaces.UserService;
import com.inventor.management.validators.EnterpriseValidator;
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
    private RolesRepository rolesRepository;
    private UserService userService;
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
        EnterpriseDto savedEnterpriseDto = dtoMapper.fromEnterprise(savedEnterprise);

        UserDto userDto = dtoMapper.fromEnterpriseUser(savedEnterpriseDto);
        UserDto savedUser = userService.saveUser(userDto);

        RolesDto rolesDto = new RolesDto();
        rolesDto.setRoleName("ADMIN");
        rolesDto.setUserDto(savedUser);
        rolesRepository.save(dtoMapper.fromRolesDto(rolesDto));

        return savedEnterpriseDto;
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid",enterpriseDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }

        Enterprise savedEnterprise = enterpriseRepository.save(dtoMapper.fromEnterpriseDto(enterpriseDto));
        return dtoMapper.fromEnterprise(savedEnterprise);
    }

    @Override
    public EnterpriseDto getEnterprise(Long id) {
        if(id == null) {
            log.error("Enterprise ID is null");
            return null;
        }
        Enterprise enterprise = enterpriseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing Enterprise with ID ="+ id + "was found in DataBase",
                        ErrorCodes.ENTERPRISE_NOT_FOUND));

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
