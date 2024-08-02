package com.inventor.management.user.resource.api;

import com.inventor.management.core.dto.ChangePasswordUserDto;
import com.inventor.management.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.inventor.management.user.roots.UserEndPoint.*;

public interface UserApi {
    @PostMapping(path = USER_ENDPOINT)
    @Operation(
            summary = "Save user",
            description = "This method allow to save user",
            tags = {"UserDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "User objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "User objet has invalid")
    })
   // @PostAuthorize("hasAuthority('USER')")
    UserDto saveUser (@RequestBody UserDto userDto);
    @PutMapping(path = UPDATE_USER_ENDPOINT)
    @Operation(
            summary = "Update User",
            description = "This method allow to update user",
            tags = {"UserDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "User objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "User objet has invalid")
    })
   // @PostAuthorize("hasAuthority('USER')")
    UserDto updateUser (@PathVariable(name = "idUser") Long userId, @RequestBody UserDto userDto);

    @GetMapping(path = FIND_USER_BY_ID)
    @Operation(
            summary = "Find out a user by ID",
            description = "This method allow to find out a user with ID",
            tags = {"UserDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "User was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing user exist in DB with ID provided")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    UserDto getUser (@PathVariable(name = "idUser") Long id);
    @GetMapping(path = USER_ENDPOINT)
    @Operation(
            summary = "Return list of users",
            description = "This method allow to research and return all users that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All users were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    List<UserDto> listUsers ();

    @DeleteMapping(path = DELETE_USER)
    @Operation(
            summary = "Delete a user",
            description = "This method allow to delete a user by ID",
            tags = {"UserDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "User has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    void deleteUser (@PathVariable(name = "idUser") Long id);

    @GetMapping(path = PROFILE_USER)
    UserDto profile (Principal principal);

    @PostMapping(path = CHANGE_PASSWORD)
    @Operation(summary = "Save user", description = "This method allow to save user", tags = {"UserDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "User objet has invalid")
    })
    UserDto changePassword (@RequestBody ChangePasswordUserDto passwordUserDto);
}
