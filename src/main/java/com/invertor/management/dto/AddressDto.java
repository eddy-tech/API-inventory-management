package com.invertor.management.dto;

import com.invertor.management.entities.Address;
import lombok.Builder;
import lombok.Data;

@Data
public class AddressDto {

    private String address1;

    private String address2;

    private String city;

    private String codePostal;

    private String country;

    /*
    public static AddressDto fromAddress (Address address){
        if(address == null){
            return null;
        }
        return AddressDto.builder()
                .address1(address.getAddress1())
                .address2(address.getAddress2())
                .city(address.getCity())
                .codePostal(address.getCodePostal())
                .country(address.getCountry())
                .build();
    }

    public static Address fromAddressDto (AddressDto addressDto){
        if(addressDto == null){
            return null;
        }
        Address address = new Address();
        address.setAddress1(addressDto.getAddress1());
        address.setAddress2(addressDto.getAddress2());
        address.setCity(addressDto.getCity());
        address.setCodePostal(addressDto.getCodePostal());
        address.setCountry(addressDto.getCountry());

        return address;
    }
    */
}
