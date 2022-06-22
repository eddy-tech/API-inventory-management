package com.inventor.management.services.auth;

import com.inventor.management.entities.User;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.repository.UserRepository;
import com.inventor.management.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User user = userService.loadUserByMail(email);
     Collection<GrantedAuthority> authorities =new ArrayList<>();
     user.getRoles().forEach(roles -> {
         authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
     });

   return new org.springframework.security.core.userdetails.User(user.getMail(),user.getPassword(),authorities);
    }
}
