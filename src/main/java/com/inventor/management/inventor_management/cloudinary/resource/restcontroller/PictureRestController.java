package com.inventor.management.inventor_management.cloudinary.resource.restcontroller;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.cloudinary.resource.api.PictureApi;
import com.inventor.management.inventor_management.cloudinary.strategy.context.StrategyImageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.inventor.management.inventor_management.core.roots.PictureEndPoint.PICTURE_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PICTURE_ENDPOINT)
public class PictureRestController implements PictureApi {
    private final StrategyImageContext strategyImageContext;

    @Override
    public ResponseEntity<?> savePicture(String context, Long id, MultipartFile file) throws ImageErrorException {
        this.strategyImageContext.savePicture(context, id, file);
        return ResponseEntity.ok("Upload picture successfully");
    }

    @Override
    public ResponseEntity<?> saveUserPicture(String id, MultipartFile file) throws ImageErrorException {
        this.strategyImageContext.saveUserPicture(id, file);
        return ResponseEntity.ok("Upload user picture successfully");
    }
}
