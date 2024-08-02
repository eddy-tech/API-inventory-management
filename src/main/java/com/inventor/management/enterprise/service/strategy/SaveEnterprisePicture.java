package com.inventor.management.enterprise.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.enterprise.dto.EnterpriseDto;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.enterprise.service.EnterpriseService;
import com.inventor.management.flickr.service.FlickrService;
import com.inventor.management.flickr.strategy.Strategy;
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
