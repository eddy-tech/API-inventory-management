package com.inventor.management.inventor_management.user.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.flickr.strategy.Strategy;
import com.inventor.management.inventor_management.user.dto.UserDto;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.flickr.service.FlickrService;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("userStrategy")
@Slf4j
@AllArgsConstructor
public class SaveUserPicture implements Strategy<UserDto> {
    private FlickrService flickrService;
    private UserService userService;

    @Override
    public UserDto savePicture(Long id,InputStream picture, String title) throws FlickrException {
        UserDto user = userService.getUser(id);
        String urlPicture = flickrService.savePicture(picture, title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of user", ErrorCodes.UPDATE_PICTURE_EXCEPTION);
        user.setPicture(urlPicture);

        return userService.saveUser(user);
    }
}
