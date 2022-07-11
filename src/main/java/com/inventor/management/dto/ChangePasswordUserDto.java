package com.inventor.management.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangePasswordUserDto {

    private Long id;
    
    private String password;

    private String confirmPassword;
}
