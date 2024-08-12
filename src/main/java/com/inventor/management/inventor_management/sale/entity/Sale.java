package com.inventor.management.inventor_management.sale.entity;

import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventes")
public class Sale extends AbstractEntity {
    @Column(name = "code_vente")
    private String codeSale;

    @ManyToOne
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @Column(name = "date_vente")
    private Instant dateSale;

    @Column(name = "commentaires")
    private String comments;

    @OneToMany(mappedBy = "sale")
    private List<SaleLine> saleLines;
}
