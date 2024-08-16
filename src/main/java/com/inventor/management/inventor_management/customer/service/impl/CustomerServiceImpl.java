package com.inventor.management.inventor_management.customer.service.impl;

import com.inventor.management.core.exceptions.BusinessException;
import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.customer.dto.CustomerRequest;
import com.inventor.management.inventor_management.customer.mapper.CustomerMapper;
import com.inventor.management.inventor_management.customerOrder.repository.CustomerOrderRepository;
import com.inventor.management.inventor_management.customer.repository.CustomerRepository;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.inventor_management.enterprise.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.inventor.management.inventor_management.core.utils.Constants.DELETE_CUSTOMER_CUSTOMER_ORDER;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerMapper customerMapper;
    private final EnterpriseMapper enterpriseMapper;
    private final ObjectValidator validator;

    @Override
    public CustomerDto saveCustomer(CustomerRequest customerRequest) {
        validator.validate(customerRequest);

        var enterprise = this.getEnterprise(customerRequest.id_enterprise());

        return customerMapper.fromCustomer(
                customerRepository.save(
                        customerMapper.fromCustomerDto(customerRequest, enterprise)
                )
        );
    }

    @Override
    public CustomerDto updateCustomer(CustomerRequest customerRequest, Long id) {
        validator.validate(customerRequest);
        var customer = this.getCustomer(id);
        var enterprise = this.getEnterprise(customerRequest.id_enterprise());

        customer.setName(customerRequest.name());
        customer.setMail(customerRequest.mail());
        customer.setPicture(customerRequest.picture());
        customer.setNumTel(customerRequest.numTel());
        customer.setSurname(customerRequest.surname());
        customer.setAddressDto(customerRequest.addressDto());
        customer.setId_enterprise(enterpriseMapper.fromEnterprise(enterprise));

        return customerMapper.fromCustomer(
                customerRepository.save(
                        customerMapper.fromCustomerDto(customerRequest, enterprise)
                )
        );
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        if(id == null){
            log.error("Customer ID is invalid");
            return null;
        }

        var customer = customerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Nothing Client with ="+id+"was found in DataBase")
                );
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDto> listCustomer() {
        return customerRepository.findAll().stream()
                .map((customerMapper::fromCustomer))
                  .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        if(id == null){
            log.error("Customer ID is null");
            return;
        }

        var customerOrders = customerOrderRepository.findAllByCustomerId(id);
        if(!customerOrders.isEmpty()){
            throw new InvalidOperationException(DELETE_CUSTOMER_CUSTOMER_ORDER);
        }

        customerRepository.deleteById(id);
    }

    private Enterprise getEnterprise(Long id) {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Enterprise not found"));
    }
}
