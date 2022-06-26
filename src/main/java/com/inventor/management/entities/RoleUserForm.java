package com.inventor.management.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "role_user_form")
public class RoleUserForm extends AbstractEntity {
    private String mail;
    private String roleName;

}
