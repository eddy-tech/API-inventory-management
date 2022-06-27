package com.inventor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class ProviderDto {

    private Long id;

    private String name;

    private String surname;

    private AddressDto addressDto;

    private String picture;

    private String mail;

    private String numTel;

    private Long id_enterprise;

    @JsonIgnore
    private List<ProviderOrderDto> providerOrdersDto;


}
