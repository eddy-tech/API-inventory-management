package com.inventor.management.routes.endpoint;

import com.inventor.management.dto.UserDto;
import com.inventor.management.routes.Constants;

import java.util.List;

public interface UserEndPoint {

 String USER_ENDPOINT = Constants.API_ROOT + "/users";
 String FIND_USER_BY_ID = USER_ENDPOINT + "/{idUser}";
 String DELETE_USER = FIND_USER_BY_ID;
}
