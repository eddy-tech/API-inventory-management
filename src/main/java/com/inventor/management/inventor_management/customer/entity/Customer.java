package com.inventor.management.inventor_management.customer.entity;

import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.core.domains.Address;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@SuperBuilder
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

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrder;
}
