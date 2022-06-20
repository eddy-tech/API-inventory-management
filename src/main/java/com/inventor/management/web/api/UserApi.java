package com.inventor.management.web.api;

import com.inventor.management.dto.UserDto;
import com.inventor.management.routes.endpoint.UserEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = UserEndPoint.USER_ENDPOINT)
public interface UserApi {

    @PostMapping(value = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Save user", notes = "This method allow to save user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "User objet has invalid")
    })
    UserDto saveUser (@RequestBody UserDto userDto);

    @PutMapping(value = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Update User",notes = "This method allow to update user",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "User objet has invalid")
    })
    UserDto updateUser (@RequestBody UserDto userDto);

    @GetMapping(value = UserEndPoint.FIND_USER_BY_ID)
    @ApiOperation(value = "Find out a user by ID",
            notes = "This method allow to find out a user with ID",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing user exist in DB with ID provided")
    })
    UserDto getUser (@PathVariable(name = "idUser") Long id);

    @GetMapping(value = UserEndPoint.USER_ENDPOINT)
    @ApiOperation(value = "Return list of users",
            notes = "This method allow to research and return all users that exist in DB",responseContainer = "List<UserDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All users were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<UserDto> listUsers ();

    @DeleteMapping(value = UserEndPoint.DELETE_USER)
    @ApiOperation(value = "Delete a user",
            notes = "This method allow to delete a user by ID",response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "User has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteUser (@PathVariable(name = "idUser") Long id);
}
