package com.inventor.management.core.web.api;

import com.inventor.management.core.dto.auth.AuthenticationRequest;
import com.inventor.management.core.dto.auth.AuthenticationResponse;
import com.inventor.management.core.roots.AuthenticationEndPoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationApi {

    @PostMapping(AuthenticationEndPoint.AUTHENTICATION_ENDPOINT + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request);

}
