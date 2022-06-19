package com.inventor.management.services.interfaces;

import com.inventor.management.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto);

    UserDto updateUser (UserDto userDto);

    UserDto getUser (Long id);

    List<UserDto> listUsers ();

    void deleteUser (Long id);
}
