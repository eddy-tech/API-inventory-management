package com.inventor.management.enterprise.service.impl;

import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.enterprise.entity.Enterprise;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.enterprise.dto.EnterpriseDto;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.user.mapper.UserMapper;
import com.inventor.management.enterprise.repository.EnterpriseRepository;
import com.inventor.management.saleLine.repository.SaleLineRepository;
import com.inventor.management.enterprise.service.EnterpriseService;
import com.inventor.management.user.service.UserService;
import com.inventor.management.validators.EnterpriseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.inventor.management.core.utils.JwtUnit.ADMIN_ROLE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final SaleLineRepository.RolesRepository rolesRepository;
    private final UserService userService;
    private final EnterpriseMapper enterpriseMapper;
    private final UserMapper userMapper;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid" + enterpriseDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }

        var enterprise = enterpriseMapper.fromEnterpriseDto(enterpriseDto);
        var savedEnterprise = enterpriseRepository.save(enterprise);
        var savedEnterpriseDto = enterpriseMapper.fromEnterprise(savedEnterprise);

        var userDto = enterpriseMapper.fromEnterpriseUser(savedEnterpriseDto);
        var savedUser = userService.saveUser(userDto);

        RolesDto rolesDto = new RolesDto();
        rolesDto.setRoleName(ADMIN_ROLE);
        rolesDto.setUserDto(savedUser);
        rolesRepository.save(userMapper.fromRolesDto(rolesDto));

        return savedEnterpriseDto;
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
        List<String> errors = EnterpriseValidator.validate(enterpriseDto);
        if(!errors.isEmpty()){
            log.error("Enterprise is invalid" + enterpriseDto);
            throw new InvalidEntityException("Enterprise is invalid", ErrorCodes.ENTERPRISE_NOT_VALID,errors);
        }

        Enterprise savedEnterprise = enterpriseRepository.save(enterpriseMapper.fromEnterpriseDto(enterpriseDto));
        return enterpriseMapper.fromEnterprise(savedEnterprise);
    }

    @Override
    public EnterpriseDto getEnterprise(Long id) {
        if(id == null) {
            log.error("Enterprise ID is null");
            return null;
        }
        var enterprise = enterpriseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing Enterprise with ID ="+ id + "was found in DataBase",
                        ErrorCodes.ENTERPRISE_NOT_FOUND));

        return enterpriseMapper.fromEnterprise(enterprise);
    }

    @Override
    public List<EnterpriseDto> listEnterprise() {
        List<Enterprise> enterpriseList = enterpriseRepository.findAll();
        return enterpriseList.stream()
                .map(enterpriseMapper::fromEnterprise)
                .collect(Collectors.toList());
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
