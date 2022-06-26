package com.inventor.management.web.restcontroller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.RoleUserForm;
import com.inventor.management.entities.User;
import com.inventor.management.services.interfaces.UserService;
import com.inventor.management.utils.JwtUnit;
import com.inventor.management.web.api.UserApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserRestController implements UserApi {

    private UserService userService;

    @Override
    public UserDto saveUser(UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @Override
    public UserDto getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }

    @Override
    public void addRoleToUser(RoleUserForm roleUserForm) {
        userService.addRoleToUser(roleUserForm.getMail(),roleUserForm.getRoleName());
    }

    @Override
    public void deleteUser(Long id) {
       userService.deleteUser(id);
    }

    @Override
    public User profile(Principal principal) {
        return userService.loadUserByMail(principal.getName());
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String authToken = request.getHeader(JwtUnit.AUTH_HEADER);
       if(authToken != null && authToken.startsWith(JwtUnit.PREFIX)){
           try{
                String jwtToken = authToken.substring(JwtUnit.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JwtUnit.SECRET_KEY);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
                String username = decodedJWT.getSubject();
                User user = userService.loadUserByMail(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(user.getMail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JwtUnit.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(roles -> roles.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", jwtToken);
                response.setContentType("authorisation/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
           } catch (Exception e) {
               throw e;
           }
       } else {
           throw new RuntimeException("Refresh Token required !!!!");
       }

    }


}
