package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface UserEndPoint {

 String USER_ENDPOINT = Constants.API_ROOT + "/users";
 String FIND_USER_BY_ID = USER_ENDPOINT + "/{idUser}";
 String DELETE_USER = FIND_USER_BY_ID;
 String PROFILE_USER = USER_ENDPOINT+ "/profile";
 String REFRESH_TOKEN_USER = Constants.API_ROOT+ "/refreshToken";
}
