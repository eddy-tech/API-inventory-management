package com.inventor.management.inventor_management.article.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.article.service.ArticleService;
import com.inventor.management.inventor_management.flickr.service.FlickrService;
import com.inventor.management.inventor_management.flickr.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

import static com.inventor.management.inventor_management.core.utils.Constants.PICTURE_ARTICLE;

@Service("articleStrategy")
@Slf4j
@RequiredArgsConstructor
public class SaveArticlePicture implements Strategy<ArticleDto> {
    private final FlickrService flickrService;
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto savePicture(Long id, InputStream picture, String title) throws FlickrException {
        var article = articleService.findById(id);
        var urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException(PICTURE_ARTICLE);
        article.setPicture(urlPicture);

        return articleMapper.fromArticleDto(articleRepository.save(article));
    }
}
