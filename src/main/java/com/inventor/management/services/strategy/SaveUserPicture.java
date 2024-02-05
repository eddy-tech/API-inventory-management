package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.UserDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.services.interfaces.FlickrService;
import com.inventor.management.services.interfaces.UserService;
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
