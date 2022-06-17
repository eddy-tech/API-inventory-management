package com.invertor.management.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.invertor.management.services.interfaces.FlickrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
@AllArgsConstructor
public class FlickrServiceImpl implements FlickrService {
    private Flickr flickr;

    @Override
    public String savePicture(InputStream picture, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String pictureId = flickr.getUploader().upload(picture,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(pictureId).getMedium640Url();
    }

}
