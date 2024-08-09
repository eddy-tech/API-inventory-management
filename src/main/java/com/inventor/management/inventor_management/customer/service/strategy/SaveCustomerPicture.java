package com.inventor.management.inventor_management.customer.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.customer.dto.CustomerDto;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.customer.service.CustomerService;
import com.inventor.management.inventor_management.flickr.service.FlickrService;
import com.inventor.management.inventor_management.flickr.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

import static com.inventor.management.inventor_management.core.utils.Constants.PICTURE_CUSTOMER;

@Service("customerStrategy")
@RequiredArgsConstructor
@Slf4j
public class SaveCustomerPicture implements Strategy<CustomerDto> {
    private final CustomerService customerService;
    private final FlickrService flickrService;

    @Override
    public CustomerDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        CustomerDto customer = customerService.getCustomer(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException(PICTURE_CUSTOMER);
        customer.setPicture(urlPicture);

        return customerService.saveCustomer(customer);
    }
}
