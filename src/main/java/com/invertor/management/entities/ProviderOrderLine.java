package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneCommandeFournisseurs")
@EqualsAndHashCode(callSuper = true)
public class ProviderOrderLine extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idCommandeFournisseur")
    private ProviderOrder providerOrder;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
