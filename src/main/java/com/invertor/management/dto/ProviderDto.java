package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
public class ProviderDto {

    private Long id;

    private Long idEnterprise;

    private String name;

    private String surname;

    private AddressDto addressDto;

    private String picture;

    private String mail;

    private String numTel;

    @JsonIgnore
    private List<ProviderOrderDto> providerOrdersDto;


}
