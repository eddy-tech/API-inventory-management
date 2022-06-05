package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Column(name = "codeCommandeFournisseur")
    private String codeProviderOrder;

    @Column(name = "dateCommande")
    private Instant dateOrdering;

    @ManyToOne
    @JoinColumn(name = "idFournisseur")
    private Provider provider;

    @OneToMany(mappedBy = "providerOrder")
    private List<ProviderOrderLine> providerOrderLines;
}
