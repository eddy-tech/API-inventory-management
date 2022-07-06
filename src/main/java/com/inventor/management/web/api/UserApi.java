package com.inventor.management.web.api;

import com.inventor.management.dto.ChangePasswordUserDto;
import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.User;
import com.inventor.management.roots.UserEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api(value = UserEndPoint.USER_ENDPOINT)
public interface UserApi {

    @PostMapping(path = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Save user", notes = "This method allow to save user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "User objet has invalid")
    })
   // @PostAuthorize("hasAuthority('USER')")
    UserDto saveUser (@RequestBody UserDto userDto);

    @PutMapping(path = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Update User",notes = "This method allow to update user",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "User objet has invalid")
    })
   // @PostAuthorize("hasAuthority('USER')")
    UserDto updateUser (@RequestBody UserDto userDto);

    @GetMapping(path = UserEndPoint.FIND_USER_BY_ID)
    @ApiOperation(value = "Find out a user by ID",
            notes = "This method allow to find out a user with ID",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing user exist in DB with ID provided")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    UserDto getUser (@PathVariable(name = "idUser") Long id);

    @GetMapping(path = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Return list of users",
            notes = "This method allow to research and return all users that exist in DB",responseContainer = "List<UserDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All users were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    List<UserDto> listUsers ();

    @DeleteMapping(path = UserEndPoint.DELETE_USER)
    @ApiOperation(value = "Delete a user",
            notes = "This method allow to delete a user by ID",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    //@PostAuthorize("hasAuthority('ADMIN')")
    void deleteUser (@PathVariable(name = "idUser") Long id);

    @GetMapping(path = UserEndPoint.PROFILE_USER)
    UserDto profile (Principal principal);

    @PostMapping(path = UserEndPoint.CHANGE_PASSWORD)
    @ApiOperation(value = "Save user", notes = "This method allow to save user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "User objet has invalid")
    })
    UserDto changePassword (@RequestBody ChangePasswordUserDto passwordUserDto);

/*
    @GetMapping(path =UserEndPoint.REFRESH_TOKEN_USER)
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception;

 */


}
