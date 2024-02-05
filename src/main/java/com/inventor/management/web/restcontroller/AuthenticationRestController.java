package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.auth.AuthenticationRequest;
import com.inventor.management.dto.auth.AuthenticationResponse;
import com.inventor.management.security.ExtendedUser;
import com.inventor.management.security.filters.JwtAuthenticationFilter;
import com.inventor.management.services.auth.ApplicationUserDetailsService;
import com.inventor.management.web.api.AuthenticationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationRestController implements AuthenticationApi {

    private final AuthenticationManager authenticationManager;
    private final ApplicationUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter authenticationFilter;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = authenticationFilter.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
