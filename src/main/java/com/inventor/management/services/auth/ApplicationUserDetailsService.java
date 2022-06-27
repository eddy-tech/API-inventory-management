package com.inventor.management.services.auth;

import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.User;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.UserRepository;
import com.inventor.management.security.ExtendedUser;
import com.inventor.management.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private StockMapperImpl dtoMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     User userMail = userService.loadUserByMail(email);
     Collection<GrantedAuthority> authorities =new ArrayList<>();
     userMail.getRoles().forEach(roles -> {
         authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
     });

        UserDto user = dtoMapper.fromUser(userMail);
        return new ExtendedUser(user.getMail(), user.getPassword(), user.getEnterpriseDto().getId(),authorities);
    }
}
