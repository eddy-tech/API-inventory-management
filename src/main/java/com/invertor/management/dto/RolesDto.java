package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesDto {

    private Long id;

    private String roleName;

    private UserDto userDto;
}
