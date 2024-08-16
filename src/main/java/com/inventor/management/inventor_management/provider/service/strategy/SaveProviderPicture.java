package com.inventor.management.inventor_management.provider.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.flickr.service.FlickrService;
import com.inventor.management.inventor_management.provider.mapper.ProviderMapper;
import com.inventor.management.inventor_management.provider.service.ProviderService;
import com.inventor.management.inventor_management.flickr.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("providerStrategy")
@RequiredArgsConstructor
@Slf4j
public class SaveProviderPicture implements Strategy<ProviderDto> {
    private final ProviderService providerService;
    private final FlickrService flickrService;
    private final ProviderMapper providerMapper;

    @Override
    public ProviderDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        ProviderDto provider = providerService.getProvider(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of provider");
        provider.setPicture(urlPicture);

        return providerService.saveProvider(providerMapper.toProviderRequest(provider));
    }
}
