package com.invertor.management.services.impl;

import com.invertor.management.dto.CustomerDto;
import com.invertor.management.entities.Customer;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.CustomerRepository;
import com.invertor.management.services.interfaces.CustomerService;
import com.invertor.management.validators.CustomerValidator;
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
            throw new InvalidEntityException("Customer isn't valid", ErrorCodes.CUSTOMER_NOT_VALID,errors);
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
            throw new InvalidEntityException("Customer isn't valid",ErrorCodes.CUSTOMER_NOT_VALID,errors);
        }

        Customer customer = dtoMapper.fromCustomerDto(customerDto);
        Customer updateCustomer = customerRepository.save(customer);
        return dtoMapper.fromCustomer(updateCustomer);
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        if(id == null){
            log.error("Customer ID isn't valid");
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
                .map((customer -> dtoMapper.fromCustomer(customer))).collect(Collectors.toList());

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
