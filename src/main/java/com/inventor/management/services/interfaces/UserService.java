package com.inventor.management.services.interfaces;

import com.inventor.management.dto.RolesDto;
import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.Roles;
import com.inventor.management.entities.User;

import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto);

    UserDto updateUser (UserDto userDto);
    RolesDto addNewRole (RolesDto appRole);
    void addRoleToUser (String email, String roleName);
    User loadUserByMail (String email);

    UserDto getUser (Long id);

    List<UserDto> listUsers ();

    void deleteUser (Long id);
}
