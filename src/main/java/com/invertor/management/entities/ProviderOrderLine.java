package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Column(name = "prixUnitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "idCommandeFournisseur")
    private ProviderOrder providerOrder;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
