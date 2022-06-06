package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;

    private String nameUser;

    private String surnameUser;

    private String mail;

    private Instant birthDate;

    private AddressDto addressDto;

    private String picture;

    private EnterpriseDto enterpriseDto;

    private List<RolesDto> rolesDto;

}
