package com.invertor.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invertor.management.entities.Address;
import com.invertor.management.entities.Category;
import com.invertor.management.entities.Customer;
import com.invertor.management.entities.CustomerOrder;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
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
