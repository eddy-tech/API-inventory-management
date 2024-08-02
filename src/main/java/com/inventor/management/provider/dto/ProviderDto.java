package com.inventor.management.provider.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.providerOrder.dto.ProviderOrderDto;
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
