package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ProviderDto {

    private Long id;

    private String name;

    private String surname;

    private AddressDto addressDto;

    private String picture;

    private String mail;

    private String numTel;

    private List<ProviderOrderDto> providerOrdersDto;


}
