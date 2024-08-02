package com.inventor.management.inventor_management.user.resource.restcontroller;

import com.inventor.management.inventor_management.user.dto.UserDto;
import com.inventor.management.inventor_management.user.resource.api.UserApi;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.user.roots.UserEndPoint.USER_ENDPOINT;


@RestController
@RequiredArgsConstructor
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
}
