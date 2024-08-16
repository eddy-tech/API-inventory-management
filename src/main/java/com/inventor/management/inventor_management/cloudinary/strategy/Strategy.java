package com.inventor.management.inventor_management.cloudinary.strategy;

import com.inventor.management.core.exceptions.ImageErrorException;
import org.springframework.web.multipart.MultipartFile;

public interface Strategy <T>{
    T saveImage(Long id, MultipartFile file) throws ImageErrorException;
}
