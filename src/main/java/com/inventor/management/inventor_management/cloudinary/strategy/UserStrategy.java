package com.inventor.management.inventor_management.cloudinary.strategy;

import org.springframework.web.multipart.MultipartFile;

public interface UserStrategy {
    void saveImage(String id, MultipartFile file);
}
