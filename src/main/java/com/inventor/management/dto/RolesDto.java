package com.inventor.management.dto;

import lombok.Data;

@Data
public class RolesDto {

    private Long id;

    private String roleName;

    private UserDto userDto;
}
