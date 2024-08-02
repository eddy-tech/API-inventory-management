package com.inventor.management.provider.entity;

import com.inventor.management.core.entities.AbstractEntity;
import com.inventor.management.core.entities.Address;
import com.inventor.management.providerOrder.entity.ProviderOrder;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fournisseurs")
@EqualsAndHashCode(callSuper = true)
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

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @OneToMany(mappedBy = "provider")
    private List<ProviderOrder> providerOrders;

}
