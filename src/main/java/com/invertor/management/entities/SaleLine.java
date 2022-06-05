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
@Table(name = "ligneCommandes")
@EqualsAndHashCode(callSuper = true)
public class SaleLine extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "idArticles")
    private Article article;
}
