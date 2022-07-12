package com.inventor.management.services.impl;


import com.inventor.management.dto.ChangePasswordUserDto;
import com.inventor.management.dto.RolesDto;
import com.inventor.management.entities.Roles;
import com.inventor.management.entities.User;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.UserDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.RolesRepository;
import com.inventor.management.repository.UserRepository;
import com.inventor.management.services.interfaces.UserService;
import com.inventor.management.validators.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private StockMapperImpl dtoMapper;


    private void validateUser (UserDto userDto){
        List<String> errors = UserValidator.validate(userDto);
        if(!errors.isEmpty()){
            log.error("User is invalid",userDto);
            throw new InvalidEntityException("User is invalid", ErrorCodes.USER_NOT_VALID,errors);
        }
    }
    @Override
    public UserDto saveUser(UserDto userDto) {
        validateUser(userDto);
        User user = dtoMapper.fromUserDto(userDto);
        User savedUser = userRepository.save(user);
        return dtoMapper.fromUser(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        validateUser(userDto);
        User updatedUser = userRepository.save(dtoMapper.fromUserDto(userDto));
        return dtoMapper.fromUser(updatedUser);
    }

    private void validatePassword(ChangePasswordUserDto passwordUserDto){

        if(passwordUserDto == null){
            log.warn("Unable to edit password with NULL object");
            throw new InvalidOperationException("Nothing information had provided for change password",
                    ErrorCodes.USER_CHANGE_PASSWORD_NOT_VALID);
        }

        if(passwordUserDto.getId() == null){
            log.warn("Unable to edit password with NULL ID");
            throw new InvalidOperationException("ID User null :: Impossible to change password",
                    ErrorCodes.USER_CHANGE_PASSWORD_NOT_VALID);
        }

        if(!StringUtils.hasLength(passwordUserDto.getPassword()) || !StringUtils.hasLength(passwordUserDto.getConfirmPassword())){
            log.warn("Unable to edit password with password NULL");
            throw new InvalidOperationException("Password user null :: Impossible to change password",
                    ErrorCodes.USER_CHANGE_PASSWORD_NOT_VALID);
        }

        if(!passwordUserDto.getPassword().equals(passwordUserDto.getConfirmPassword())){
            log.warn("Unable to edit password with two different passwords");
            throw new InvalidOperationException("Password user no conform :: Impossible to change password",
                    ErrorCodes.USER_CHANGE_PASSWORD_NOT_VALID);
        }
    }

    @Override
    public UserDto changePassword(ChangePasswordUserDto passwordUserDto) {
        validatePassword(passwordUserDto);
        Optional<User> user = userRepository.findById(passwordUserDto.getId());
        if(user.isEmpty()){
            log.warn("No User were found with ID ="+passwordUserDto.getId());
            throw new EntityNotFoundException("No User were found with ID ="+passwordUserDto.getId(),ErrorCodes.USER_NOT_FOUND);
        }
        User changeUser = user.get(); // RECUPERER LE USER
        changeUser.setPassword(passwordUserDto.getPassword());

        return dtoMapper.fromUser(userRepository.save(changeUser));
    }

    @Override
    public RolesDto addNewRole(RolesDto rolesDto) {
        Roles roles = dtoMapper.fromRolesDto(rolesDto);
        Roles addRole = rolesRepository.save(roles);
        return dtoMapper.fromRoles(addRole);
    }
    @Override
    public UserDto loadUserByMail(String email) {
        User user = userRepository.findByMail(email)
                .orElseThrow(()-> new EntityNotFoundException("Nothing user with mail ="+ email +"was found in database",
                        ErrorCodes.USER_NOT_FOUND));

        return dtoMapper.fromUser(user);
    }
    @Override
    public void addRoleToUser(String email, String roleName) {
        UserDto userDto = loadUserByMail(email);
        Roles roles = rolesRepository.findByRoleName(roleName);

        User user = dtoMapper.fromUserDto(userDto);
        user.getRoles().add(roles);
    }

    @Override
    public UserDto getUser(Long id) {
        if(id == null) {
            log.error("User ID is null");
            return null;
        }

        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Nothing User with ID ="+ id + "was found in DataBase",
                        ErrorCodes.USER_NOT_FOUND));
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
