package com.inventor.management.inventor_management.user.service.impl;

import com.inventor.management.inventor_management.user.service.RoleService;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final UserService userService;
    private final Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    private String realm;

    @Override
    public void assignRole(String userId, String roleName) {
        var user = userService.getUser(userId);
        var representation = this.getRole(roleName);
        log.info("Assigning role has been requested with successfully");

        user.roles().realmLevel().add(Collections.singletonList(representation));
    }

    @Override
    public void deleteRoleFromUser(String userId, String roleName) {
        var user = userService.getUser(userId);
        var representation = this.getRole(roleName);
        log.info("Removing role has been requested with successfully");

        user.roles().realmLevel().remove(Collections.singletonList(representation));
    }

    private RoleRepresentation getRole(String roleName) {
        return this.getRolesResource().get(roleName).toRepresentation();
    }

    private RolesResource getRolesResource() { return keycloak.realm(realm).roles(); }
}
