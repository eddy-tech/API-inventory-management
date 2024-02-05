package com.inventor.management.services.impl;

import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.dto.SaleLineDto;
import com.inventor.management.entities.CustomerOrderLine;
import com.inventor.management.entities.ProviderOrderLine;
import com.inventor.management.entities.SaleLine;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.*;
import com.inventor.management.repository.CustomerOrderLineRepository;
import com.inventor.management.repository.ProviderOrderLineRepository;
import com.inventor.management.repository.SaleLineRepository;
import com.inventor.management.services.interfaces.ArticleService;
import com.inventor.management.dto.ArticleDto;
import com.inventor.management.entities.Article;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.validators.ArticleValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CustomerOrderLineRepository customerOrderLineRepository;
    private final ProviderOrderLineRepository providerOrderLineRepository;
    private final SaleLineRepository saleLineRepository;
    private final ArticleMapper articleMapper;
    private final SaleMapper saleMapper;
    private final CustomerMapper customerMapper;
    private final ProviderMapper providerMapper;


    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty()){
            log.error("Article is invalid" + articleDto);
            throw new InvalidEntityException("Article is invalid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }

        var saveArticles = articleRepository.save(articleMapper.fromArticleDto(articleDto));
        return articleMapper.fromArticle(saveArticles);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if(!errors.isEmpty())
            throw new InvalidEntityException("Article is not exit", ErrorCodes.ARTICLES_NOT_FOUND,errors);

        var updateArticle = articleRepository.save(articleMapper.fromArticleDto(articleDto));
        return articleMapper.fromArticle(updateArticle);
    }

    @Override
    public ArticleDto getArticle(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return null;
        }
      var article = articleRepository.findById(id).orElseThrow(()->
              new EntityNotFoundException(
                      "Nothing article with ID ="+id+"has been found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND)
      );

      return articleMapper.fromArticle(article);
    }

    @Override
    public ArticleDto getCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle))
            throw new EntityNotFoundException(
                    "Nothing Article with CODE ="+codeArticle+ "has been found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND
            );
        var article = articleRepository.findByCodeArticle(codeArticle);

       return articleMapper.fromArticle(article);
    }

    @Override
    public List<ArticleDto> listArticle() {
        List<Article> articleList = articleRepository.findAll();

        return articleList.stream()
                .map(articleMapper::fromArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {
        return articleRepository.findAllByCategoryId(categoryId).stream()
                .map(articleMapper::fromArticle)
                .collect(Collectors.toList());
    }

    @Override
    public List<SaleLineDto> findHistorySales(Long articleId) {
        List<SaleLine> saleLineList = saleLineRepository.findAllByArticleId(articleId);

        return saleLineList.stream()
                .map(saleMapper::fromSaleLine)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerOrderLineDto> findHistoryCustomerOrder(Long articleId) {
        return customerOrderLineRepository.findAllByArticleId(articleId).stream()
                .map(customerMapper::fromCustomerOrderLine)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderOrderLineDto> findHistoryProviderOrder(Long articleId) {
        return providerOrderLineRepository.findAllByArticleId(articleId).stream()
                .map(providerMapper::fromProviderOrderLine)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return;
        }

        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByArticleId(id);
        if(!customerOrderLineList.isEmpty()){
            throw new InvalidOperationException(
                    "Unable to delete an article that already use in customer order",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE
            );
        }

        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByArticleId(id);
        if(!providerOrderLineList.isEmpty()){
            throw new InvalidOperationException(
                    "Unable to delete an article that already use in provider order",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE
            );
        }

        List<SaleLine> saleLineList = saleLineRepository.findAllByArticleId(id);
        if(!saleLineList.isEmpty()){
            throw new InvalidOperationException(
                    "Unable to delete an article that already use in sale ",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE
            );
        }

        articleRepository.deleteById(id);
    }
}
