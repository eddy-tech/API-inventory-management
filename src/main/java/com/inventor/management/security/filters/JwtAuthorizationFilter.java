package com.inventor.management.security.filters;

import com.inventor.management.services.auth.ApplicationUserDetailsService;
import com.inventor.management.utils.JwtUnit;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(JwtUnit.AUTH_HEADER);
        String userEmail = null;
        String idEnterprise = null;
        String jwtToken = null;

        if(authHeader != null && authHeader.startsWith(JwtUnit.PREFIX)){
            jwtToken = authHeader.substring(JwtUnit.SUB_STRING);
            userEmail = authenticationFilter.extractUsername(jwtToken);
            idEnterprise = authenticationFilter.extractIdEnterprise(jwtToken);
        }
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(authenticationFilter.validateToken(jwtToken,userDetails)){
              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetails, null, userDetails.getAuthorities()
              );
              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
         MDC.put("idEnterprise",idEnterprise);
        filterChain.doFilter(request,response);
      }

    }


