package com.invertor.management.dto;

import com.invertor.management.entities.Address;
import com.invertor.management.entities.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
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
