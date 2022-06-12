package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractEntity {

    @Column(name = "idEnterprise")
    private Long enterprise;

    @Column(name = "nom")
    private String name;

    @Column(name = "prenom")
    private String surname;

    @Embedded // Champs composé capable d'être utiliser dans les autres entités
    private Address adresse;

    @Column(name = "photo")
    private String picture;

    @Column(name = "email")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrder;
}
