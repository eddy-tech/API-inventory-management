package com.inventor.management.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inventor.management.core.dto.AddressDto;
import com.inventor.management.customerOrder.dto.CustomerOrderDto;
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

    private Long id_enterprise;

    @JsonIgnore
    private List<CustomerOrderDto> customerOrderDto;


}
