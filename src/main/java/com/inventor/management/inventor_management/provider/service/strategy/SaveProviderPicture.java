package com.inventor.management.inventor_management.provider.service.strategy;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import com.inventor.management.inventor_management.provider.mapper.ProviderMapper;
import com.inventor.management.inventor_management.provider.repository.ProviderRepository;
import com.inventor.management.inventor_management.provider.service.ProviderService;
import com.inventor.management.inventor_management.cloudinary.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("providerStrategy")
@RequiredArgsConstructor
@Slf4j
public class SaveProviderPicture implements Strategy<ProviderDto> {
    private final ProviderService providerService;
    private final ProviderRepository providerRepository;
    private final CloudinaryService cloudinaryService;
    private final ProviderMapper providerMapper;


    @Override
    public ProviderDto saveImage(Long id, MultipartFile file) throws ImageErrorException {
        var provider = providerService.findById(id);
        var urlPicture = cloudinaryService.uploadPicture(file);
        if(!StringUtils.hasLength(urlPicture))
            throw new ImageErrorException("Error saving picture of provider");

        provider.setPicture(urlPicture);
        return providerMapper.fromProvider(providerRepository.save(provider));
    }
}
