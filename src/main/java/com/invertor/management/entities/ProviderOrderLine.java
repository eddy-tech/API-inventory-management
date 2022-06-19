package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneCommandeFournisseurs")
@EqualsAndHashCode(callSuper = true)
public class ProviderOrderLine extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "prix_unitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_commande_fournisseur")
    private ProviderOrder providerOrder;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;
}
