package com.inventor.management.security;

import com.inventor.management.security.filters.JwtAuthenticationFilter;
import com.inventor.management.security.filters.JwtAuthorizationFilter;
import com.inventor.management.utils.JwtUnit;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/**/refreshToken",
                "/**/authenticate", "/**/enterprise","/v2/api-docs",
                "/swagger-resources","/swagger-resources/**","/configuration/ui","/configuration/security",
                "/swagger-ui.html","/webjars/**","/v3/api-docs/**","/swagger-ui/**")
                .permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/users/**").hasAuthority(JwtUnit.ENTERPRISE_ROLE);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority(JwtUnit.USER_ROLE);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
