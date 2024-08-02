package com.inventor.management.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.inventor_management.user.dto.UserDto;
import lombok.Data;

@Data
public class RolesDto {

    private Long id;

    private String roleName;

    @JsonIgnore
    private UserDto userDto;
}
