package com.inventor.management.security.filters;

import com.inventor.management.security.ExtendedUser;
import com.inventor.management.utils.JwtUnit;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtAuthenticationFilter {

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractIdEnterprise(String token) {
        final Claims claims = extractAllClaims(token);

        return claims.get("idEnterprise", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JwtUnit.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(ExtendedUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public String createToken(Map<String, Object> claims, ExtendedUser user){
        return Jwts.builder().setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuedAt(new Date(System.currentTimeMillis() + JwtUnit.EXPIRE_ACCESS_TOKEN))
                .claim("idEnterprise", user.getId_enterprise().toString())
                .signWith(SignatureAlgorithm.HS256, JwtUnit.SECRET_KEY).compact();
    }

    public Boolean validateToken (String token, UserDetails userDetails){
        final String username =extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



}
