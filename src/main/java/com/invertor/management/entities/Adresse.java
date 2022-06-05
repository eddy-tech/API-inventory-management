package com.invertor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse {

    @Column(name = "adresse1")
    private String adress1;

    @Column(name = "adresse2")
    private String adress2;

    @Column(name = "ville")
    private String city;

    @Column(name = "codePostal")
    private String postalCode;

    @Column(name = "pays")
    private String country;
}
