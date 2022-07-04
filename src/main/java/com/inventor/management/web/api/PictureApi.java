package com.inventor.management.web.api;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.ArticleDto;
import com.inventor.management.roots.ArticleEndPoint;
import com.inventor.management.roots.PictureEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Api(PictureEndPoint.PICTURE_ENDPOINT)
public interface PictureApi {

    @PostMapping(value = PictureEndPoint.SAVE_PICTURE,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save picture", notes = "This method allow to save a picture", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Picture objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 400, message = "Picture objet has invalid")
    })
    Object savePicture(String context, Long id, @RequestPart(name = "file") MultipartFile picture, String title) throws IOException, FlickrException;
}
