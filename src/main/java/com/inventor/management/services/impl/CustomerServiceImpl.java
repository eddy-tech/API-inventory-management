package com.inventor.management.services.impl;

import com.inventor.management.entities.Customer;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.CustomerDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.CustomerRepository;
import com.inventor.management.services.interfaces.CustomerService;
import com.inventor.management.validators.CustomerValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private StockMapperImpl dtoMapper;


    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        List<String> errors = CustomerValidator.validate(customerDto);
        if(!errors.isEmpty()) {
            log.error("Customer isn't valid",customerDto);
            throw new InvalidEntityException("Customer is invalid", ErrorCodes.CUSTOMER_NOT_VALID,errors);
        }

        Customer customer = dtoMapper.fromCustomerDto(customerDto);
        Customer saveCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(saveCustomer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        List<String> errors = CustomerValidator.validate(customerDto);
        if(!errors.isEmpty()){
            log.error("Customer isn't valid",customerDto);
            throw new InvalidEntityException("Customer is invalid",ErrorCodes.CUSTOMER_NOT_VALID,errors);
        }

        Customer updateCustomer = customerRepository.save(dtoMapper.fromCustomerDto(customerDto));
        return dtoMapper.fromCustomer(updateCustomer);
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        if(id == null){
            log.error("Customer ID is invalid");
            return null;
        }

        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Client with ="+id+"was found in DataBase", ErrorCodes.CUSTOMER_NOT_VALID));
        return dtoMapper.fromCustomer(customer);
    }

    @Override
    public List<CustomerDto> listCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = customerList.stream()
                .map((customer -> dtoMapper.fromCustomer(customer)))
                  .collect(Collectors.toList());

        return customerDtoList;
    }

    @Override
    public void deleteCustomer(Long id) {
        if(id == null){
            log.error("Customer ID is null");
            return;
        }
        customerRepository.deleteById(id);

    }
}
