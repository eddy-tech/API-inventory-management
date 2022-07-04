package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.EnterpriseDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.services.interfaces.EnterpriseService;
import com.inventor.management.services.interfaces.FlickrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("enterpriseStrategy")
@Slf4j
@AllArgsConstructor
public class SaveEnterprisePicture implements Strategy<EnterpriseDto> {

    private EnterpriseService enterpriseService;
    private FlickrService flickrService;

    @Override
    public EnterpriseDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        EnterpriseDto enterprise = enterpriseService.getEnterprise(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of enterprise", ErrorCodes.UPDATE_PICTURE_EXCEPTION);
        enterprise.setPicture(urlPicture);

        return enterpriseService.saveEnterprise(enterprise);
    }
}
