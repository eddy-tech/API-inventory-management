package com.inventor.management.inventor_management.cloudinary.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadPicture(final MultipartFile file);
}
