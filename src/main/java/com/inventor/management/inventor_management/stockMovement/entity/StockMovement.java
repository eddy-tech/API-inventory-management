package com.inventor.management.inventor_management.stockMovement.entity;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.core.enums.SourceStockMovement;
import com.inventor.management.inventor_management.core.enums.TypeMoveStock;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mouvementStock")
@SuperBuilder
public class StockMovement extends AbstractEntity {
    @Column(name = "date_mouvement")
    private Instant dateMovement;

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "type_move_stock")
    private TypeMoveStock typeMoveStock;

    @Column(name = "source_move_stock")
    private SourceStockMovement sourceStockMovement;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;
}
