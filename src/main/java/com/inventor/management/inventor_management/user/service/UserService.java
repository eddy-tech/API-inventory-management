package com.inventor.management.inventor_management.user.service;

import com.inventor.management.core.dto.ChangePasswordUserDto;
import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.inventor_management.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto);
    UserDto updateUser (UserDto userDto);
    UserDto loadUserByMail (String email);
    UserDto getUser (Long id);
    List<UserDto> listUsers ();
    void deleteUser (Long id);

}
