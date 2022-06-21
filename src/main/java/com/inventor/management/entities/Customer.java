package com.inventor.management.entities;

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

    @Column(name = "nom")
    private String name;

    @Column(name = "prenom")
    private String surname;

    @Embedded // Champs composé capable d'être utiliser dans les autres entités
    private Address address;

    @Column(name = "photo")
    private String picture;

    @Column(name = "email")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrder;
}
