package com.inventor.management.inventor_management.flickr.resource.api;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.core.roots.PictureEndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureApi {
    @PostMapping(
            value = PictureEndPoint.SAVE_PICTURE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Save picture", description = "This method allow to save a picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Picture objet has invalid")
    })
    Object savePicture(String context, Long id, @RequestPart(name = "file") MultipartFile picture, String title)
            throws IOException, FlickrException;
}
