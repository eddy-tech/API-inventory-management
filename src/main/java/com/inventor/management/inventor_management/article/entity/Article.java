package com.inventor.management.inventor_management.article.entity;

import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import com.inventor.management.inventor_management.stockMovement.entity.StockMovement;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "articles")
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
