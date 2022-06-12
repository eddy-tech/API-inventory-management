package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "idEnterprise")
    private Long enterprise;

    @Column(name = "photo")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<SaleLine> saleLines;

    @OneToMany(mappedBy = "article")
    private List<ProviderOrderLine> providerOrderLines;

    @OneToMany(mappedBy = "article")
    private List<StockMovement> stockMovements;


}
