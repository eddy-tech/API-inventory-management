package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneCommandes")
@EqualsAndHashCode(callSuper = true)
public class SaleLine extends AbstractEntity {

    @Column(name = "idEnterprise")
    private Long idEnterprise;

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "prixUnitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "idArticles")
    private Article article;
}
