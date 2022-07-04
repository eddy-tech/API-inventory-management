package com.inventor.management.entities;

import com.inventor.management.enums.SourceStockMovement;
import com.inventor.management.enums.TypeMoveStock;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mouvementStock")
@EqualsAndHashCode(callSuper = true)
public class StockMovement extends AbstractEntity {

    @Column(name = "date_mouvement")
    private Instant dateMovement;

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "type_move_stock")
    private TypeMoveStock typeMoveStock;

    @Column(name = "source_move_stock")
    private SourceStockMovement sourceStockMovement;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;
}
