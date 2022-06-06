package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
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
