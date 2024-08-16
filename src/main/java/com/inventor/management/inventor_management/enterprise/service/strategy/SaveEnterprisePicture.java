package com.inventor.management.inventor_management.enterprise.service.strategy;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.enterprise.dto.EnterpriseDto;
import com.inventor.management.inventor_management.enterprise.mapper.EnterpriseMapper;
import com.inventor.management.inventor_management.enterprise.repository.EnterpriseRepository;
import com.inventor.management.inventor_management.enterprise.service.EnterpriseService;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import com.inventor.management.inventor_management.cloudinary.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("enterpriseStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveEnterprisePicture implements Strategy<EnterpriseDto> {
    private final EnterpriseService enterpriseService;
    private final EnterpriseRepository enterpriseRepository;
    private final CloudinaryService cloudinaryService;
    private final EnterpriseMapper enterpriseMapper;

    @Override
    public EnterpriseDto saveImage(Long id, MultipartFile file) throws ImageErrorException {
        var enterprise = enterpriseService.findById(id);
        var urlPicture = cloudinaryService.uploadPicture(file);
        if(!StringUtils.hasLength(urlPicture))
            throw new ImageErrorException("Error saving picture of enterprise");

        enterprise.setPicture(urlPicture);
        return enterpriseMapper.fromEnterprise(enterpriseRepository.save(enterprise));
    }
}
