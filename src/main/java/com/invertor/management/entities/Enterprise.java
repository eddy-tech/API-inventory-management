package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "entreprises")
@EqualsAndHashCode(callSuper = true)
public class Enterprise extends AbstractEntity{

    @Column(name = "nomEnterprise")
    private String name;
}
