package com.invertor.management.entities;

import com.invertor.management.enums.TypeMoveStock;
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

    @Column(name = "idEnterprise")
    private Long idEnterprise;

    @Column(name = "dateMouvement")
    private Instant dateMovement;

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "typeMoveStock")
    private TypeMoveStock typeMoveStock;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
