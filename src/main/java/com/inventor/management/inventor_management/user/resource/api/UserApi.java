package com.inventor.management.inventor_management.user.resource.api;

import com.inventor.management.inventor_management.user.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.inventor.management.inventor_management.user.roots.UserEndPoint.*;


public interface UserApi {
    @PostMapping
    @Operation(
            summary = "Save User Keycloak",
            description = "This method allow to save user keycloak",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "User objet has been saved"),
                    @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
                    @ApiResponse(responseCode = "404",description = "User objet has invalid")
            })
    ResponseEntity<?> createUser(@RequestBody User user);

    @PutMapping(VERIFY_EMAIL_ENDPOINT)
    @Operation(
            summary = " Verify user send mail",
            description = "This method allow to verify user send mail",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "User objet has been verified"),
                    @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
                    @ApiResponse(responseCode = "404",description = "User objet has invalid")
            })
    ResponseEntity<?> verifyUser(@PathVariable(name = "id") String userId);

    @DeleteMapping(DELETE_USER_ENDPOINT)
    @Operation(
            summary = "Delete a user",
            description = "This method allow to delete a user by ID",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "User has been deleted"),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
            })
    ResponseEntity<?> deleteUser(@PathVariable(name = "id") String userId);

    @PutMapping(FORGOT_PASSWORD_ENDPOINT)
    @Operation(
            summary = "Forgot user password",
            description = "This method allows to change the forgotten user password",
            tags = {"User"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",description = "User password has been updated"),
                    @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
                    @ApiResponse(responseCode = "404",description = "User objet has invalid")
            })
    ResponseEntity<?> forgotPassword(@RequestParam String username);

    @GetMapping(USER_ROLES_ENDPOINT)
    @Operation(
            summary = "Return list of user roles",
            description = "This method allows to return all user roles"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All user roles were found in Keycloak DB / Empty list",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = User.class))
                                    )
                            }
                    ),
                    @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
            })
    ResponseEntity<?> getUserRoles(@PathVariable(name = "id") String userId);

}
