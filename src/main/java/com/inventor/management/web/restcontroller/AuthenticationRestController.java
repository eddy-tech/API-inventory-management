package com.inventor.management.web.restcontroller;

import com.inventor.management.auth.AuthenticationRequest;
import com.inventor.management.auth.AuthenticationResponse;
import com.inventor.management.routes.endpoint.AuthenticationEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AuthenticationEndPoint.AUTHENTICATION_ENDPOINT)
public class AuthenticationRestController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        return ResponseEntity.ok(AuthenticationResponse.builder().jwtToken("eddy_invalid_koko").build());

    }


}
