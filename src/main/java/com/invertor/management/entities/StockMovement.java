package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mouvementStock")
@EqualsAndHashCode(callSuper = true)
public class StockMovement extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
