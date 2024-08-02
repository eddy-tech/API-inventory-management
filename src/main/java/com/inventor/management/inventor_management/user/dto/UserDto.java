package com.inventor.management.inventor_management.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.core.dto.RolesDto;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String nameUser;
    private String surnameUser;
    private String mail;
    private String password;
    private Instant birthDate;
    private AddressDto addressDto;
    private String picture;
    private EnterpriseDto enterpriseDto;

    @JsonIgnore
    private List<RolesDto> rolesDto;

}
