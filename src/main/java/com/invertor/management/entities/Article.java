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
@Table(name = "articles")
@EqualsAndHashCode(callSuper = true)
public class Article extends AbstractEntity {
    @Column(name = "codearticles")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixUnitaireHorsTaxe")
    private BigDecimal unitPriceHt;

    @Column(name = "taux TVA")
    private BigDecimal rateTax;

    @Column(name = "prixUnitaireTTC")
    private BigDecimal unitPriceTtc;

    @Column(name = "photo")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private Category category;

}
