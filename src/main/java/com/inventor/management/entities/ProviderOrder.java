package com.inventor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commandeFournisseurs")
@EqualsAndHashCode(callSuper = true)
public class ProviderOrder extends AbstractEntity {

    @Column(name = "code_commande_fournisseur")
    private String codeProviderOrder;

    @Column(name = "date_commande")
    private Instant dateOrdering;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Provider provider;

    @OneToMany(mappedBy = "providerOrder")
    private List<ProviderOrderLine> providerOrderLines;
}
