package com.inventor.management.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    private Long id;

    private String name;

    private String surname;

    private AddressDto addressDto;

    private String picture;

    private String mail;

    private String numTel;

    private List<CustomerOrderDto> customerOrderDto;


}
