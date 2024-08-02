package com.inventor.management.enterprise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.user.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseDto {

    private Long id;

    private String name;

    private String description;

    private AddressDto addressDto;

    private String codeFiscal;

    private String picture;

    private String mail;

    private String numTel;

    private String siteWeb;

    @JsonIgnore
    private List<UserDto> usersDto;

}
