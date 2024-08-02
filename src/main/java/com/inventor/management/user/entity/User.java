package com.inventor.management.user.entity;

import com.inventor.management.enterprise.entity.Enterprise;
import com.inventor.management.core.entities.AbstractEntity;
import com.inventor.management.core.entities.Address;
import com.inventor.management.core.entities.Roles;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    @Column(name = "nom")
    private String nameUser;

    @Column(name = "prenom")
    private String surnameUser;

    @Column(name = "email")
    private String mail;

    @Column(name = "date_naissance")
    private Instant birthDate;

    @Column(name = "mot_de_passe")
    private String password;

    @Embedded
    private Address address;

    @Column(name = "photo")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "user")
    private List<Roles> roles;

    public User (String email, String password, List<Roles> roles){
        this.mail = email;
        this.password = password;
        this.roles = roles;
    }
}
