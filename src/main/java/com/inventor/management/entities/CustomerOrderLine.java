package com.inventor.management.entities;

import lombok.*;

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

    @Column(name = "prix_unitaire") // Prix d'achat d'un article
    private BigDecimal unitPrice;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @ManyToOne
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "id_commande_client")
    private CustomerOrder customerOrder;
}
