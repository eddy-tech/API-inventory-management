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
@Entity
public class Address implements Serializable {

    @Column(name = "adresse1")
    private String address1;

    @Column(name = "adresse2")
    private String address2;

    @Column(name = "ville")
    private String city;

    @Column(name = "codePostal")
    private String codePostal;

    @Column(name = "pays")
    private String country;
}
