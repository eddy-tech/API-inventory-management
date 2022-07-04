package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.CustomerDto;
import com.inventor.management.entities.CustomerOrder;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.services.interfaces.CustomerService;
import com.inventor.management.services.interfaces.FlickrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("customerStrategy")
@AllArgsConstructor
@Slf4j
public class SaveCustomerPicture implements Strategy<CustomerDto> {

    private CustomerService customerService;
    private FlickrService flickrService;

    @Override
    public CustomerDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        CustomerDto customer = customerService.getCustomer(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of customer", ErrorCodes.UPDATE_PICTURE_EXCEPTION);
        customer.setPicture(urlPicture);

        return customerService.saveCustomer(customer);
    }
}
