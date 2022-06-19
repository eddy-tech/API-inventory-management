package com.inventor.management.services.impl;


import com.inventor.management.entities.User;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.UserDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.UserRepository;
import com.inventor.management.services.interfaces.UserService;
import com.inventor.management.validators.UserValidator;
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
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private StockMapperImpl dtoMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if(!errors.isEmpty()){
            log.error("User is invalid",userDto);
            throw new InvalidEntityException("User is invalid", ErrorCodes.USER_NOT_VALID,errors);
        }

        User user = dtoMapper.fromUserDto(userDto);
        User savedUser = userRepository.save(user);
        return dtoMapper.fromUser(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        List<String> errors = UserValidator.validate(userDto);
        if(!errors.isEmpty()){
            log.error("User is invalid",userDto);
            throw new InvalidEntityException("User isn invalid", ErrorCodes.USER_NOT_VALID,errors);
        }

        User updatedUser = userRepository.save(dtoMapper.fromUserDto(userDto));
        return dtoMapper.fromUser(updatedUser);
    }

    @Override
    public UserDto getUser(Long id) {
        if(id == null) {
            log.error("User ID is null");
            return null;
        }

        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing User with ID ="+ id + "was found in DataBase",ErrorCodes.USER_NOT_FOUND));
        return dtoMapper.fromUser(user);
    }

    @Override
    public List<UserDto> listUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> dtoMapper.fromUser(user)).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteUser(Long id) {
        if(id == null){
            log.error("id is invalid");
            return;
        }

        userRepository.deleteById(id);

    }
}