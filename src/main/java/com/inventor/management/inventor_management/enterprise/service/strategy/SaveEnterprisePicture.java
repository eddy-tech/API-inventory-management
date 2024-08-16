package com.inventor.management.inventor_management.enterprise.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.enterprise.service.EnterpriseService;
import com.inventor.management.inventor_management.flickr.service.FlickrService;
import com.inventor.management.inventor_management.flickr.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("enterpriseStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveEnterprisePicture implements Strategy<EnterpriseDto> {
    private final EnterpriseService enterpriseService;
    private final FlickrService flickrService;

    @Override
    public EnterpriseDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        var enterprise = enterpriseService.getEnterprise(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of enterprise");
        enterprise.setPicture(urlPicture);

        return enterpriseService.saveEnterprise(enterprise);
    }
}
