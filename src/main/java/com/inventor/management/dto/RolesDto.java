package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RolesDto {

    private Long id;

    private String roleName;

    @JsonIgnore
    private UserDto userDto;
}
