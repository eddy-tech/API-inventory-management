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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateurs")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity{

    @Column(name = "nomUtilisateur")
    private String nameUser;
}
