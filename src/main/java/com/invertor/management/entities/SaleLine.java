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

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "prix_unitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_vente")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_articles")
    private Article article;
}
