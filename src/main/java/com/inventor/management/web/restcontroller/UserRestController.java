package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.User;
import com.inventor.management.services.interfaces.UserService;
import com.inventor.management.utils.JwtUnit;
import com.inventor.management.web.api.UserApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserRestController implements UserApi {

    private UserService userService;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
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
    public User profile(Principal principal) {
        return userService.loadUserByMail(principal.getName());
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
