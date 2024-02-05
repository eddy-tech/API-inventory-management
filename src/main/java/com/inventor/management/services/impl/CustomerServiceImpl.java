package com.inventor.management.services.impl;

import com.inventor.management.entities.Customer;
import com.inventor.management.entities.CustomerOrder;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.CustomerDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.CustomerMapper;
import com.inventor.management.repository.CustomerOrderRepository;
import com.inventor.management.repository.CustomerRepository;
import com.inventor.management.services.interfaces.CustomerService;
import com.inventor.management.validators.CustomerValidator;
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
        List<String> errors = CustomerValidator.validate(customerDto);
        if(!errors.isEmpty()) {
            log.error("Customer isn't valid" + customerDto);
            throw new InvalidEntityException(
                    "Customer is invalid", ErrorCodes.CUSTOMER_NOT_VALID, errors
            );
        }
        var customer = customerMapper.fromCustomerDto(customerDto);
        var saveCustomer = customerRepository.save(customer);

        return customerMapper.fromCustomer(saveCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        List<String> errors = CustomerValidator.validate(customerDto);
        if(!errors.isEmpty()){
            log.error("Customer isn't valid" + customerDto);
            throw new InvalidEntityException(
                    "Customer is invalid", ErrorCodes.CUSTOMER_NOT_VALID, errors
            );
        }

        var updateCustomer = customerRepository.save(customerMapper.fromCustomerDto(customerDto));
        return customerMapper.fromCustomer(updateCustomer);
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
