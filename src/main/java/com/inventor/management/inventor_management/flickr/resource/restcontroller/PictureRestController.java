package com.inventor.management.inventor_management.flickr.resource.restcontroller;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.flickr.strategy.StrategyPictureContext;
import com.inventor.management.inventor_management.flickr.resource.api.PictureApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.inventor.management.core.roots.PictureEndPoint.PICTURE_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PICTURE_ENDPOINT)
public class PictureRestController implements PictureApi {
    private final StrategyPictureContext strategyPictureContext;

    @Override
    public Object savePicture(String context, Long id, MultipartFile picture, String title)
            throws IOException, FlickrException {
        return strategyPictureContext.savePicture(context,id,picture.getInputStream(),title);
    }
}
