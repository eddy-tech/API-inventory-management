package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "entreprises")
@EqualsAndHashCode(callSuper = true)
public class Enterprise extends AbstractEntity{

    @Column(name = "nomEnterprise")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private Adresse address;

    @Column(name = "codeFiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String picture;

    @Column(name = "email")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "siteWeb")
    private String siteWeb;

    @OneToMany(mappedBy = "enterprise")
    private List<User> users;
}
