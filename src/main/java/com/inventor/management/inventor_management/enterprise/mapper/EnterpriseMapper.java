package com.inventor.management.inventor_management.enterprise.mapper;

import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.user.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseMapper {
    public EnterpriseDto fromEnterprise (Enterprise enterprise){
        EnterpriseDto enterpriseDto = new EnterpriseDto();
        BeanUtils.copyProperties(enterprise,enterpriseDto);
        return enterpriseDto;
    }

    public Enterprise fromEnterpriseDto (EnterpriseDto enterpriseDto){
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(enterpriseDto,enterprise);
        return enterprise;
    }

    public UserDto fromEnterpriseUser (EnterpriseDto enterpriseDto){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(enterpriseDto,userDto);
        return userDto;
    }
}
