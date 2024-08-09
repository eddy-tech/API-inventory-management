package com.inventor.management.inventor_management.user.service;

import com.inventor.management.inventor_management.core.domains.User;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

public interface UserService {
    void createUser(User user);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
    void forgotPassword(String username);
    UserResource getUser(String userId);
    List<RoleRepresentation> getUserRoles(String userId);
}
