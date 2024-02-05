package com.inventor.management.services.auth;

import com.inventor.management.dto.UserDto;
import com.inventor.management.entities.User;
import com.inventor.management.mapper.StockMapper;
import com.inventor.management.mapper.UserMapper;
import com.inventor.management.security.ExtendedUser;
import com.inventor.management.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
     UserDto userDto = userService.loadUserByMail(email);
     Collection<GrantedAuthority> authorities =new ArrayList<>();

     User user = userMapper.fromUserDto(userDto);
     user.getRoles().forEach(roles -> {
         authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
     });

     UserDto saveUser = userMapper.fromUser(user);
      return new ExtendedUser(saveUser.getMail(),saveUser.getPassword(),saveUser.getEnterpriseDto().getId(),authorities);
    }
}
