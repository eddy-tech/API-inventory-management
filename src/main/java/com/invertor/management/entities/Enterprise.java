package com.invertor.management.entities;

import lombok.*;

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
    private Address address;

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
