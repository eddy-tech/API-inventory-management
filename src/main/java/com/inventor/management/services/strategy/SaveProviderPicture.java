package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.ProviderDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.services.interfaces.FlickrService;
import com.inventor.management.services.interfaces.ProviderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("providerStrategy")
@AllArgsConstructor
@Slf4j
public class SaveProviderPicture implements Strategy<ProviderDto> {

    private ProviderService providerService;
    private FlickrService flickrService;

    @Override
    public ProviderDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        ProviderDto provider = providerService.getProvider(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of provider", ErrorCodes.UPDATE_PICTURE_EXCEPTION);
        provider.setPicture(urlPicture);

        return providerService.saveProvider(provider);
    }
}
