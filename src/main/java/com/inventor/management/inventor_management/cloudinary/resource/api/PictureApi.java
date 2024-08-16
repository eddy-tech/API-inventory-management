package com.inventor.management.inventor_management.cloudinary.resource.api;

import com.inventor.management.core.exceptions.ImageErrorException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


import static com.inventor.management.inventor_management.core.roots.PictureEndPoint.SAVE_PICTURE;
import static com.inventor.management.inventor_management.core.roots.PictureEndPoint.SAVE_PICTURE_USER;

public interface PictureApi {
    @PostMapping(
            value = SAVE_PICTURE
    )
    @Operation(summary = "Save picture", description = "This method allow to save a picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Picture objet has invalid")
    })
    ResponseEntity<?> savePicture(@RequestParam String context, @PathVariable Long id, @RequestPart MultipartFile file)
            throws ImageErrorException;

    @PostMapping(
            value = SAVE_PICTURE_USER
    )
    @Operation(summary = "Save user picture", description = "This method allow to save a user picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Picture objet has invalid")
    })
    ResponseEntity<?> saveUserPicture(@PathVariable String id, @RequestPart MultipartFile file)
            throws ImageErrorException;
}
