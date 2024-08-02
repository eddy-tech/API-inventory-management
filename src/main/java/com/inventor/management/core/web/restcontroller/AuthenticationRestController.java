package com.inventor.management.core.web.restcontroller;

import com.inventor.management.core.dto.auth.AuthenticationRequest;
import com.inventor.management.core.dto.auth.AuthenticationResponse;
import com.inventor.management.core.security.ExtendedUser;
import com.inventor.management.core.security.filters.JwtAuthenticationFilter;
import com.inventor.management.core.auth.ApplicationUserDetailsService;
import com.inventor.management.core.web.api.AuthenticationApi;
import lombok.RequiredArgsConstructor;
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
