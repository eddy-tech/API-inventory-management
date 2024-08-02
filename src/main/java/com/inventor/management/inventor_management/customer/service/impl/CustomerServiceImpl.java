package com.inventor.management.inventor_management.customer.service.impl;

import com.inventor.management.inventor_management.customer.entity.Customer;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.customer.mapper.CustomerMapper;
import com.inventor.management.inventor_management.customerOrder.repository.CustomerOrderRepository;
import com.inventor.management.inventor_management.customer.repository.CustomerRepository;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.core.validators.CustomerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.fromCustomer(
                customerRepository.save(
                        customerMapper.fromCustomerDto(customerDto)
                )
        );
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        var customer = this.getCustomer(id);
        customer.setName(customerDto.getName());
        customer.setMail(customerDto.getMail());
        customer.setPicture(customerDto.getPicture());
        customer.setNumTel(customerDto.getNumTel());
        customer.setSurname(customerDto.getSurname());
        customer.setAddressDto(customerDto.getAddressDto());

        return customerMapper.fromCustomer(
                customerRepository.save(
                        customerMapper.fromCustomerDto(customer)
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
                        "Nothing Client with ="+id+"was found in DataBase", ErrorCodes.CUSTOMER_NOT_VALID)
                );
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDto> listCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map((customerMapper::fromCustomer))
                  .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Long id) {
        if(id == null){
            log.error("Customer ID is null");
            return;
        }

        List<CustomerOrder> customerOrders = customerOrderRepository.findAllByCustomerId(id);
        if(!customerOrders.isEmpty()){
            throw new InvalidOperationException(
                    "Unable to delete a customer that has already customer orders ",
                    ErrorCodes.USER_NOT_ALREADY_IN_USE
            );
        }

        customerRepository.deleteById(id);
    }
}
