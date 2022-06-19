package com.inventor.management.dto;

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

    private List<UserDto> usersDto;


}
