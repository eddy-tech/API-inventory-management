package com.invertor.management.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {

    @Column(name = "adresse_1")
    private String address1;

    @Column(name = "adresse_2")
    private String address2;

    @Column(name = "ville")
    private String city;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "pays")
    private String country;

}
