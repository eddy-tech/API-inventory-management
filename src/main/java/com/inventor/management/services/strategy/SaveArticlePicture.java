package com.inventor.management.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.inventor.management.dto.ArticleDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.services.interfaces.ArticleService;
import com.inventor.management.services.interfaces.FlickrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("articleStrategy")
@Slf4j
@AllArgsConstructor
public class SaveArticlePicture implements Strategy<ArticleDto>{

    private FlickrService flickrService;
    private ArticleService articleService;

    @Override
    public ArticleDto savePicture(Long id, InputStream picture, String title) throws FlickrException {
        ArticleDto article = articleService.getArticle(id);
        String urlPicture = flickrService.savePicture(picture,title);
        if(!StringUtils.hasLength(urlPicture))
            throw new InvalidOperationException("Error saving picture of article", ErrorCodes.UPDATE_PICTURE_EXCEPTION);
        article.setPicture(urlPicture);

        return articleService.saveArticle(article);
    }
}
