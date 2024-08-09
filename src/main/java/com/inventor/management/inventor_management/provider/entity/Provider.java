package com.inventor.management.inventor_management.provider.entity;

import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.core.domains.Address;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.providerOrder.entity.ProviderOrder;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fournisseurs")
public class Provider extends AbstractEntity {
    @Column(name = "nom")
    private String name;

    @Column(name = "prenom")
    private String surname;

    @Embedded // Champs composé capable d'être utilisé dans les autres entités
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

    @OneToMany(mappedBy = "provider")
    private List<ProviderOrder> providerOrders;
}
