package com.inventor.management.inventor_management.user.resource.restcontroller;

import com.inventor.management.inventor_management.user.dto.User;
import com.inventor.management.inventor_management.user.resource.api.UserApi;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.inventor.management.inventor_management.user.roots.UserEndPoint.USER_ENDPOINT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_ENDPOINT)
public class UserRestController implements UserApi {
    private final UserService userService;
    @Override
    public ResponseEntity<?> createUser(User user) {
        userService.createUser(user);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    public ResponseEntity<?> verifyUser(String userId) {
        userService.sendVerificationEmail(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deleteUser(String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> forgotPassword(String username) {
        userService.forgotPassword(username);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> getUserRoles(String userId) {
        return ResponseEntity.status(OK).body(userService.getUserRoles(userId));
    }
}
