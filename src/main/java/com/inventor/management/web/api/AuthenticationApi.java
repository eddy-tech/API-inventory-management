package com.inventor.management.web.api;

import com.inventor.management.dto.auth.AuthenticationRequest;
import com.inventor.management.dto.auth.AuthenticationResponse;
import com.inventor.management.roots.AuthenticationEndPoint;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(AuthenticationEndPoint.AUTHENTICATION_ENDPOINT)
public interface AuthenticationApi {

    @PostMapping(AuthenticationEndPoint.AUTHENTICATION_ENDPOINT + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request);

}
