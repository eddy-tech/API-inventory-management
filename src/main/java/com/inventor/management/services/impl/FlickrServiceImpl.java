package com.inventor.management.services.impl;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.inventor.management.services.interfaces.FlickrService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlickrServiceImpl implements FlickrService {
    private final Flickr flickr;

    @Override
    public String savePicture(InputStream picture, String title) throws FlickrException {

        UploadMetaData uploadMetaData = new UploadMetaData();
        uploadMetaData.setTitle(title);

        String pictureId = flickr.getUploader().upload(picture,uploadMetaData);
        return flickr.getPhotosInterface().getPhoto(pictureId).getMedium640Url();
    }

}
