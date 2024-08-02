package com.inventor.management.inventor_management.user.service.impl;


import com.inventor.management.core.dto.ChangePasswordUserDto;
import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.inventor_management.user.dto.UserDto;
import com.inventor.management.inventor_management.user.entity.User;
import com.inventor.management.inventor_management.user.mapper.UserMapper;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.saleLine.repository.SaleLineRepository;
import com.inventor.management.inventor_management.user.repository.UserRepository;
import com.inventor.management.inventor_management.user.service.UserService;
import com.inventor.management.core.validators.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;


    private void validateUser (UserDto userDto){
        List<String> errors = UserValidator.validate(userDto);
        if(!errors.isEmpty()){
            log.error("User is invalid" + userDto);
            throw new InvalidEntityException("User is invalid", ErrorCodes.USER_NOT_VALID,errors);
        }
    }
    @Override
    public UserDto saveUser(UserDto userDto) {
        this.validateUser(userDto);
        var savedUser = userRepository.save(userMapper.fromUserDto(userDto));
        return userMapper.fromUser(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        validateUser(userDto);
        var updatedUser = userRepository.save(userMapper.fromUserDto(userDto));
        return userMapper.fromUser(updatedUser);
    }

    @Override
    public UserDto loadUserByMail(String email) {
        var user = userRepository.findByMail(email)
                .orElseThrow(()-> new EntityNotFoundException("Nothing user with mail ="+ email +"was found in database",
                        ErrorCodes.USER_NOT_FOUND));

        return userMapper.fromUser(user);
    }

    @Override
    public UserDto getUser(Long id) {
        if(id == null) {
            log.error("User ID is null");
            return null;
        }

        var user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing User with ID ="+ id + "was found in DataBase",
                        ErrorCodes.USER_NOT_FOUND));
        return userMapper.fromUser(user);
    }

    @Override
    public List<UserDto> listUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
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
