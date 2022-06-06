package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invertor.management.entities.Address;
import com.invertor.management.entities.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
public class EnterpriseDto {

    private Long id;

    private String name;

    private String description;

    private AddressDto addressDto;

    @JsonIgnore
    private String codeFiscal;

    private String picture;

    private String mail;

    private String numTel;

    private String siteWeb;

    @JsonIgnore
    private List<UserDto> usersDto;

}
