package com.invertor.management.entities;

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
public class User extends AbstractEntity{

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
    @JoinColumn(name = "id_entreprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "user")
    private List<Roles> roles;
}
