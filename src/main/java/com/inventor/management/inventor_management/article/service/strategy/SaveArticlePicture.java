package com.inventor.management.inventor_management.article.service.strategy;

import com.inventor.management.core.exceptions.ImageErrorException;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.article.service.ArticleService;
import com.inventor.management.inventor_management.cloudinary.service.CloudinaryService;
import com.inventor.management.inventor_management.cloudinary.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service("articleStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveArticlePicture implements Strategy<ArticleDto> {
    private final CloudinaryService cloudinaryService;
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;


    @Override
    public ArticleDto saveImage(Long id, MultipartFile file) throws ImageErrorException {
        var article = articleService.findById(id);
        var urlPicture = cloudinaryService.uploadPicture(file);
        if(!StringUtils.hasLength(urlPicture)) {
            throw new ImageErrorException("Error uploading file to Cloudinary");
        }

        article.setPicture(urlPicture);
        return articleMapper.fromArticleDto(articleRepository.save(article));
    }
}
