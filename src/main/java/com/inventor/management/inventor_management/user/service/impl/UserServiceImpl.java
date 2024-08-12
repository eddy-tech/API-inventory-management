package com.inventor.management.inventor_management.user.service.impl;

import com.inventor.management.inventor_management.user.dto.User;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static org.keycloak.representations.idm.CredentialRepresentation.PASSWORD;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;
    @Override
    public void createUser(User user) {
        var representation = getUserRepresentation(user);

        var usersResource = this.getUsersResource();
        var response = usersResource.create(representation);
        log.info("Status code:" + response.getStatus());

        if(!Objects.equals(201, response.getStatus())) {
            throw new RuntimeException("Status code " + response.getStatus());
        }
        log.info("User created successfully");

        var usersList = usersResource.searchByUsername(user.username(), true);
        var userRepresentation = usersList.getFirst();
        sendVerificationEmail(userRepresentation.getId());
    }

    @Override
    public void sendVerificationEmail(String userId) {
        var usersResource = this.getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        var usersResource = this.getUsersResource();
        usersResource.delete(userId);
    }

    @Override
    public void forgotPassword(String username) {
        var usersResource = this.getUsersResource();
        var usersList = usersResource.searchByUsername(username, true);
        var userRepresentation = usersList.getFirst();

        var userResource = usersResource.get(userRepresentation.getId());
        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }

    @Override
    public UserResource getUser(String userId) {
        var usersResource = this.getUsersResource();
        return usersResource.get(userId);
    }

    @Override
    public List<RoleRepresentation> getUserRoles(String userId) {
        return getUser(userId).roles().realmLevel().listAll();
    }

    private static UserRepresentation getUserRepresentation(User user) {
        var representation = new UserRepresentation();
        representation.setEnabled(false);
        representation.setUsername(user.username());
        representation.setFirstName(user.firstName());
        representation.setLastName(user.lastName());
        representation.setEmail(user.email());
        representation.setEmailVerified(false);

        var credential = new CredentialRepresentation();
        credential.setValue(user.password());
        credential.setType(PASSWORD);

        representation.setCredentials(List.of(credential));
        return representation;
    }

    private UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }
}
