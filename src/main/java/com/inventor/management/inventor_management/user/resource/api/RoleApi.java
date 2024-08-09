package com.inventor.management.inventor_management.user.resource.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.inventor.management.inventor_management.user.roots.UserEndPoint.ASSIGN_ROLES_ENDPOINT;
import static com.inventor.management.inventor_management.user.roots.UserEndPoint.DELETE_ROLE_USER_ENDPOINT;

public interface RoleApi {
    @PutMapping(ASSIGN_ROLES_ENDPOINT)
    @Operation(
            summary = "Assign a role to user",
            description = "This method allows to assign a role to a user",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "User objet has been assigned a role"),
                    @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
                    @ApiResponse(responseCode = "404",description = "User objet has invalid")
            })
    ResponseEntity<?> assignRole(
            @PathVariable(name = "id") String userId, @RequestParam String roleName
    );

    @DeleteMapping(DELETE_ROLE_USER_ENDPOINT)
    @Operation(
            summary = "Delete a role",
            description = "This method allow to delete a role by ID",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "Role has been deleted"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
            })
    ResponseEntity<?> deleteRoleFromUser(
            @PathVariable(name = "id") String userId,@RequestParam String roleName
    );
}
