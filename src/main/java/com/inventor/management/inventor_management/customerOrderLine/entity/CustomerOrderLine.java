package com.inventor.management.inventor_management.customerOrderLine.entity;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneCommandeClients")
public class CustomerOrderLine extends AbstractEntity {

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "prix_unitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "id_commande_client")
    private CustomerOrder customerOrder;
}
