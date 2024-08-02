package com.inventor.management.user.service;

import com.inventor.management.core.dto.ChangePasswordUserDto;
import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto);
    UserDto updateUser (UserDto userDto);
    UserDto changePassword (ChangePasswordUserDto passwordUserDto);
    RolesDto addNewRole (RolesDto appRole);
    void addRoleToUser (String email, String roleName);
    UserDto loadUserByMail (String email);
    UserDto getUser (Long id);
    List<UserDto> listUsers ();
    void deleteUser (Long id);

}
