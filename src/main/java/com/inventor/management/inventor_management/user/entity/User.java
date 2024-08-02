package com.inventor.management.inventor_management.user.entity;

import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.domains.Address;
import com.inventor.management.inventor_management.domains.Roles;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
public class User extends AbstractEntity {
    @Column(name = "id_keycloak", unique = true)
    private String keycloakId;

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
}
