package com.inventor.management.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "articles")
@EqualsAndHashCode(callSuper = true)
public class Article extends AbstractEntity {
    @Column(name = "code_articles")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix_unitaire_hors_taxe")
    private BigDecimal unitPriceHt;

    @Column(name = "taux_TVA")
    private BigDecimal rateTax;

    @Column(name = "prix_unitaireTTC")
    private BigDecimal unitPriceTtc;

    @Column(name = "photo")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "id_categories")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "article")
    private List<SaleLine> saleLines;

    @OneToMany(mappedBy = "article")
    private List<ProviderOrderLine> providerOrderLines;

    @OneToMany(mappedBy = "article")
    private List<StockMovement> stockMovements;


}
