package com.inventor.management.web.restcontroller;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.services.strategy.StrategyPictureContext;
import com.inventor.management.web.api.PictureApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@AllArgsConstructor
public class PictureRestController implements PictureApi {
    private final StrategyPictureContext strategyPictureContext;

    @Override
    public Object savePicture(String context, Long id, MultipartFile picture, String title) throws IOException, FlickrException {
        return strategyPictureContext.savePicture(context,id,picture.getInputStream(),title);
    }
}
