package com.inventor.management.inventor_management.cloudinary.service.impl;

import com.cloudinary.Cloudinary;
import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.core.utils.FileUploadUtil;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public String uploadPicture(final MultipartFile file) {
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.generateFileName(file.getOriginalFilename());
        return this.uploadFile(file, fileName);
    }

    private String uploadFile(MultipartFile file, String fileName) {
        try {
            final Map result = cloudinary.uploader()
                    .upload(file.getBytes(), Map.of("public_id", "dev/article/" + fileName));

            log.info("Successfully uploaded file " + fileName);

            return (String) result.get("secure_url");
        } catch (Exception e) {
            throw new ImageErrorException("Error uploading file to Cloudinary");
        }
    }
}
