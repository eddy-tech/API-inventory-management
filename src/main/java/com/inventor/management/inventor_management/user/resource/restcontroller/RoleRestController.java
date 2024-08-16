package com.inventor.management.inventor_management.user.resource.restcontroller;

import com.inventor.management.inventor_management.user.resource.api.RoleApi;
import com.inventor.management.inventor_management.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.inventor.management.inventor_management.user.roots.UserEndPoint.ROLES_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROLES_ENDPOINT)
public class RoleRestController implements RoleApi {
    private final RoleService roleService;
    @Override
    public ResponseEntity<?> assignRole(String userId, String roleName) {
        roleService.assignRole(userId, roleName);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @Override
    public ResponseEntity<?> deleteRoleFromUser(String userId, String roleName) {
        roleService.deleteRoleFromUser(userId, roleName);
        return ResponseEntity.noContent().build();
    }
}
