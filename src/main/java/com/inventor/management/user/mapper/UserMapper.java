package com.inventor.management.user.mapper;

import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.user.dto.UserDto;
import com.inventor.management.core.entities.Address;
import com.inventor.management.core.entities.Roles;
import com.inventor.management.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDto fromUser (User user){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    public User fromUserDto (UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }

    public AddressDto fromAddress (Address address){
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return addressDto;
    }

    public Address fromAddressDto (AddressDto addressDto){
        Address address = new Address();
        BeanUtils.copyProperties(addressDto,address);
        return address;
    }

    public RolesDto fromRoles (Roles roles){
        RolesDto rolesDto = new RolesDto();
        BeanUtils.copyProperties(roles,rolesDto);
        return rolesDto;
    }

    public Roles fromRolesDto (RolesDto rolesDto){
        Roles roles = new Roles();
        BeanUtils.copyProperties(rolesDto,roles);
        return roles;
    }
}
