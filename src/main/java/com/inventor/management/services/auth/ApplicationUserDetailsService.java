package com.inventor.management.services.auth;

import com.inventor.management.entities.User;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByMail(email)
                .orElseThrow(()->new EntityNotFoundException("Nothing user with email provided",
                        ErrorCodes.CUSTOMER_NOT_FOUND));
        return (UserDetails) new User(user.getMail(),user.getPassword(),Collections.emptyList());
    }
}
