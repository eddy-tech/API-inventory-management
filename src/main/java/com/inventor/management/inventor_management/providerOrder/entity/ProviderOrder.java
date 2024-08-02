package com.inventor.management.inventor_management.providerOrder.entity;

import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import com.inventor.management.core.enums.StateOrder;
import com.inventor.management.inventor_management.provider.entity.Provider;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commandeFournisseurs")
public class ProviderOrder extends AbstractEntity {

    @Column(name = "code_commande_fournisseur")
    private String codeProviderOrder;

    @Column(name = "date_commande")
    private Instant dateOrdering;

    @Column(name = "etat_commande")
    private StateOrder stateOrder;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Provider provider;

    @OneToMany(mappedBy = "providerOrder")
    private List<ProviderOrderLine> providerOrderLines;
}
