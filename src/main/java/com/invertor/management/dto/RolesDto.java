package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RolesDto {

    private Long id;

    private Long idEnterprise;

    private String roleName;

    private UserDto userDto;
}
