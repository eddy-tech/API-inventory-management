package com.inventor.management.inventor_management.domains;

import com.inventor.management.inventor_management.user.entity.User;
import jakarta.persistence.*;
import lombok.*;


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
