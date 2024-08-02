package com.inventor.management.user.resource.restcontroller;

import com.inventor.management.core.dto.ChangePasswordUserDto;
import com.inventor.management.user.dto.UserDto;
import com.inventor.management.user.service.UserService;
import com.inventor.management.user.resource.api.UserApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.inventor.management.user.roots.UserEndPoint.USER_ENDPOINT;


@RestController
@AllArgsConstructor
@RequestMapping(USER_ENDPOINT)
public class UserRestController implements UserApi {

    private final UserService userService;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @Override
    public UserDto getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }

    @Override
    public void deleteUser(Long id) {
       userService.deleteUser(id);
    }

    @Override
    public UserDto profile(Principal principal) {
        return userService.loadUserByMail(principal.getName());
    }

    @Override
    public UserDto changePassword(ChangePasswordUserDto passwordUserDto) {
        return userService.changePassword(passwordUserDto);
    }

}
