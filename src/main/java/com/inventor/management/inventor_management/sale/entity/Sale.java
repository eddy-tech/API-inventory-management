package com.inventor.management.inventor_management.sale.entity;

import com.inventor.management.inventor_management.domains.AbstractEntity;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @Column(name = "date_vente")
    private Instant dateSale;

    @Column(name = "commentaires")
    private String comments;

    @OneToMany(mappedBy = "sale")
    private List<SaleLine> saleLines;
}
