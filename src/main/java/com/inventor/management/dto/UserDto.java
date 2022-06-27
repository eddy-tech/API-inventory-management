package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private List<RolesDto> rolesDto;

}
