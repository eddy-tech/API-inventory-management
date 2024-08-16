package com.inventor.management.inventor_management.user.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface UserEndPoint {
    String USER_ENDPOINT = API_ROOT + "/users";
    String VERIFY_EMAIL_ENDPOINT = "/{id}/send-verify-email";
    String DELETE_USER_ENDPOINT = "/{id}";
    String FORGOT_PASSWORD_ENDPOINT = "/forgot-password";
    String USER_ROLES_ENDPOINT = "/{id}/roles";
    String ROLES_ENDPOINT = API_ROOT + "/roles";
    String ASSIGN_ROLES_ENDPOINT = "/assign/users/{id}";
    String DELETE_ROLE_USER_ENDPOINT = "/remove/users/{id}";
}
