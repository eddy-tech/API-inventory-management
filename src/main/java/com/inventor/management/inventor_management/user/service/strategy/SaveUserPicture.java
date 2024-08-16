package com.inventor.management.inventor_management.user.service.strategy;

import com.inventor.management.core.exceptions.BusinessException;
import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import com.inventor.management.inventor_management.cloudinary.strategy.UserStrategy;
import com.inventor.management.inventor_management.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service("userStrategy")
@RequiredArgsConstructor
@Slf4j
public class SaveUserPicture implements UserStrategy{
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    @Override
    public void saveImage(String id, MultipartFile file) {
        var user = userService.getUser(id).toRepresentation();
        var imageUrl = cloudinaryService.uploadPicture(file);

        if(!StringUtils.hasLength(imageUrl)) {
            throw new ImageErrorException("Error uploading file to Cloudinary");
        }

        Map<String, List<String>> attributes = user.getAttributes();
        if(attributes == null) {
            throw new BusinessException("Impossible to upload file with no existing attributes");
        }
        user.setAttributes(attributes);
        log.info("Upload file successfully {}", user.getAttributes());

        userService.getUser(id).update(user);
    }
}
