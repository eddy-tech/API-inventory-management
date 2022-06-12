package com.invertor.management.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class Roles extends AbstractEntity{

    @Column(name = "idEnterprise")
    private Long enterprise;

    @Column(name = "roleName")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
