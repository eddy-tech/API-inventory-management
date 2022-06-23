package com.inventor.management.security.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class ExtendedUser extends User {

    private Long idEnterprise;

    public ExtendedUser (String username, String password, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
    }

    public ExtendedUser (String username, String password, Long idEnterprise, Collection<? extends GrantedAuthority> authorities){
        super(username, password, authorities);
        this.idEnterprise = idEnterprise;
    }

    public Long getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(Long idEnterprise) {
        this.idEnterprise = idEnterprise;
    }
}
