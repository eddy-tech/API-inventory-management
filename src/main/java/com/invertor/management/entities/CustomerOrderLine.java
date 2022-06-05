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
@Table(name = "ligneCommandeClients")
@EqualsAndHashCode(callSuper = true)
public class CustomerOrderLine extends AbstractEntity{

    @Column(name = "quantite")
    private BigDecimal quantity;

    @Column(name = "prixUnitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idCommandeClient")
    private CustomerOrder customerOrder;
}
