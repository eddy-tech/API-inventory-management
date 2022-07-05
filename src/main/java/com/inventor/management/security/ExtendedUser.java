package com.inventor.management.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ExtendedUser extends User {
    @Getter
    @Setter
    private Long id_enterprise;

    public ExtendedUser (String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }

    public ExtendedUser(String username, String password, Long idEnterprise, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.id_enterprise = idEnterprise;
    }
}
