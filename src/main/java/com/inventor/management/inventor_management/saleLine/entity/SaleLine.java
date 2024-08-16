package com.inventor.management.inventor_management.saleLine.entity;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.sale.entity.Sale;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneVentes")
@SuperBuilder
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
