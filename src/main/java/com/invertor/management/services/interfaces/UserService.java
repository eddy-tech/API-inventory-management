package com.invertor.management.services.interfaces;

import com.invertor.management.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto);

    UserDto updateUser (UserDto userDto);

    UserDto getUser (Long id);

    List<UserDto> listUsers ();

    void deleteUser (Long id);
}
