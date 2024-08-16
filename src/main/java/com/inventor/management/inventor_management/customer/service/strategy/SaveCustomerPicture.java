package com.inventor.management.inventor_management.customer.service.strategy;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.inventor_management.customer.mapper.CustomerMapper;
import com.inventor.management.inventor_management.customer.repository.CustomerRepository;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import com.inventor.management.inventor_management.cloudinary.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import static com.inventor.management.inventor_management.core.utils.Constants.PICTURE_CUSTOMER;

@Service("customerStrategy")
@RequiredArgsConstructor
@Slf4j
public class SaveCustomerPicture implements Strategy<CustomerDto> {
    private final CustomerService customerService;
    private final CloudinaryService cloudinaryService;
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto saveImage(Long id, MultipartFile file) throws ImageErrorException {
        var customer = customerService.findById(id);
        var urlPicture = cloudinaryService.uploadPicture(file);
        if(!StringUtils.hasLength(urlPicture))
            throw new ImageErrorException(PICTURE_CUSTOMER);

        customer.setPicture(urlPicture);
        return customerMapper.fromCustomer(customerRepository.save(customer));
    }
}
