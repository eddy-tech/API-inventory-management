package com.inventor.management.flickr.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface Strategy <T>{

    T savePicture (Long id, InputStream picture, String title) throws FlickrException;
}
