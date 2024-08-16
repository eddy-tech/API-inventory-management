package com.inventor.management.inventor_management.customer.mapper;

import com.inventor.management.inventor_management.core.mapper.AddressMapper;
import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.dto.CustomerRequest;
import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CustomerMapper {
    private final AddressMapper addressMapper;
    private final EnterpriseMapper enterpriseMapper;

    public Customer fromCustomerDto (CustomerRequest customerRequest, Enterprise enterprise){
        return Customer.builder()
                .name(customerRequest.name())
                .surname(customerRequest.surname())
                .mail(customerRequest.mail())
                .address(addressMapper.toAddress(customerRequest.addressDto()))
                .numTel(customerRequest.numTel())
                .picture(customerRequest.picture())
                .enterprise(enterprise)
                .creationTime(Instant.now())
                .build();
    }

    public CustomerDto fromCustomer (Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .mail(customer.getMail())
                .addressDto(addressMapper.fromAddress(customer.getAddress()))
                .numTel(customer.getNumTel())
                .picture(customer.getPicture())
                .id_enterprise(enterpriseMapper.fromEnterprise(customer.getEnterprise()))
                .build();
    }

    public CustomerRequest toCustomerRequest(CustomerDto customerDto) {
        return CustomerRequest.builder()
               .name(customerDto.getName())
               .surname(customerDto.getSurname())
               .mail(customerDto.getMail())
               .addressDto(customerDto.getAddressDto())
               .numTel(customerDto.getNumTel())
               .picture(customerDto.getPicture())
                .id_enterprise(customerDto.getId_enterprise().getId())
               .build();
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .mail(customerDto.getMail())
                .address(addressMapper.toAddress(customerDto.getAddressDto()))
                .numTel(customerDto.getNumTel())
                .picture(customerDto.getPicture())
                .enterprise(enterpriseMapper.fromEnterpriseDto(customerDto.getId_enterprise()))
                .build();
    }
}
