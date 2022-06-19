package com.inventor.management.services.impl;

import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.services.interfaces.ArticleService;
import com.inventor.management.dto.ArticleDto;
import com.inventor.management.entities.Article;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.validators.ArticleValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
            log.error("Article is invalid",articleDto);
            throw new InvalidEntityException("Article is invalid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        Article article = dtoMapper.fromArticleDto(articleDto);
        Article saveArticles = articleRepository.save(article);
        return dtoMapper.fromArticle(saveArticles);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()) throw new InvalidEntityException("Article is not exit", ErrorCodes.ARTICLES_NOT_FOUND,errors);

        Article updateArticle = articleRepository.save(dtoMapper.fromArticleDto(articleDto));
        return dtoMapper.fromArticle(updateArticle);
    }

    @Override
    public ArticleDto getArticle(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return null;
        }
      Article article = articleRepository.findById(id).orElseThrow(()->
              new EntityNotFoundException("Nothing article with ID ="+id+"was found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND));

      return dtoMapper.fromArticle(article);
    }

    @Override
    public ArticleDto getCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle))
            throw new EntityNotFoundException("Nothing Article with CODE ="+codeArticle+ "has been found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND);
        Article article = articleRepository.findArticleByCodeArticle(codeArticle);

       return dtoMapper.fromArticle(article);
    }

    @Override
    public List<ArticleDto> listArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDto> articleDtoList =articleList.stream()
                .map(article -> dtoMapper.fromArticle(article)).collect(Collectors.toList());

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
