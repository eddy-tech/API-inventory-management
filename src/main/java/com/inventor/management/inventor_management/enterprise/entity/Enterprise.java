package com.inventor.management.inventor_management.enterprise.entity;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.domains.Address;
import com.inventor.management.inventor_management.provider.entity.Provider;
import com.inventor.management.inventor_management.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "entreprises")
public class Enterprise extends AbstractEntity {

    @Column(name = "nom_enterprise")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    @Column(name = "code_fiscal")
    private String codeFiscal;

    @Column(name = "photo")
    private String picture;

    @Column(name = "email")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "site_web")
    private String siteWeb;

    @OneToMany(mappedBy = "enterprise")
    private List<User> users;

    @OneToMany(mappedBy = "enterprise")
    private List<Article> articles;

    @OneToMany(mappedBy = "enterprise")
    private List<Customer> customers;

    @OneToMany(mappedBy = "enterprise")
    private List<Provider> providers;
}
