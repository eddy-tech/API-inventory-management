package com.inventor.management.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class Roles extends AbstractEntity{

    @Column(name = "role_name")
    private String roleName;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
