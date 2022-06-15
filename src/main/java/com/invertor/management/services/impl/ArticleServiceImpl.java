package com.invertor.management.services.impl;

import com.invertor.management.dto.ArticleDto;
import com.invertor.management.entities.Article;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.ArticleRepository;
import com.invertor.management.services.interfaces.ArticleService;
import com.invertor.management.validators.ArticleValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private StockMapperImpl dtoMapper;

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()){
            log.error("Article isn't valid {}",articleDto);
            throw new InvalidEntityException("Article isn't valid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        Article article = dtoMapper.fromArticleDto(articleDto);
        Article saveArticles = articleRepository.save(article);
        return dtoMapper.fromArticle(saveArticles);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()) throw new InvalidEntityException("Article isn't exit", ErrorCodes.ARTICLES_NOT_FOUND,errors);

        Article article = dtoMapper.fromArticleDto(articleDto);
        Article updateArticle = articleRepository.save(article);
        return dtoMapper.fromArticle(updateArticle);
    }

    @Override
    public ArticleDto findById(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return null;
        }
      Article article = articleRepository.findById(id).orElseThrow(()->
              new EntityNotFoundException("Nothing article with ID ="+id+"has been found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND));

      return dtoMapper.fromArticle(article);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle))
            throw new EntityNotFoundException("Nothing Article with CODE ="+codeArticle+ "has been found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND);
        Article article = articleRepository.findArticleByCodeArticle(codeArticle);

       return dtoMapper.fromArticle(article);
    }

    @Override
    public List<ArticleDto> listArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDto> articleDtoList =articleList.stream().map(article -> dtoMapper.fromArticle(article)).collect(Collectors.toList());

        return articleDtoList;
    }

    @Override
    public void deleteArticle(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return;
        }
     articleRepository.deleteById(id);
    }
}
