package com.inventor.management.inventor_management.core.mapper;

import com.inventor.management.inventor_management.core.domains.Address;
import com.inventor.management.inventor_management.core.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressDto fromAddress (Address address) {
        return AddressDto.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .country(address.getCountry())
                .codePostal(address.getCodePostal())
                .build();
    }

    public Address toAddress (AddressDto addressDto) {
        return Address.builder()
               .address1(addressDto.getAddress1())
               .address2(addressDto.getAddress2())
               .city(addressDto.getCity())
               .country(addressDto.getCountry())
               .codePostal(addressDto.getCodePostal())
               .build();
    }

}
