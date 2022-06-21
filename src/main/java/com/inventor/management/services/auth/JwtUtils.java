package com.inventor.management.services.auth;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    private String SECRET_KEY = "secret";

    public String extractUsername (String token){
       return null;
    }
}
