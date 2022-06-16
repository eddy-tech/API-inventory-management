package com.invertor.management.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ventes")
@EqualsAndHashCode(callSuper = true)
public class Sale extends AbstractEntity{

    @Column(name = "idEnterprise")
    private Long idEnterprise;

    @Column(name = "codeVente")
    private String codeSale;

    @Column(name = "dateVente")
    private Instant dateSale;

    @Column(name = "commentaires")
    private String comments;

    @OneToMany(mappedBy = "sale")
    private List<SaleLine> saleLines;
}
