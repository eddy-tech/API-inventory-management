package com.inventor.management.inventor_management.article.service.impl;

import com.inventor.management.core.exceptions.BusinessException;
import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.category.repository.CategoryRepository;
import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.article.service.ArticleService;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.customerOrderLine.mapper.CustomerOrderLineMapper;
import com.inventor.management.inventor_management.enterprise.service.EnterpriseService;
import com.inventor.management.inventor_management.provider.mapper.ProviderMapper;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.sale.mapper.SaleMapper;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.customerOrderLine.repository.CustomerOrderLineRepository;
import com.inventor.management.inventor_management.providerOrderLine.repository.ProviderOrderLineRepository;
import com.inventor.management.inventor_management.saleLine.repository.SaleLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;

import static com.inventor.management.inventor_management.core.utils.Constants.*;
import static com.inventor.management.inventor_management.core.utils.RandomGenerator.generateRandomCode;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerOrderLineRepository customerOrderLineRepository;
    private final ProviderOrderLineRepository providerOrderLineRepository;
    private final SaleLineRepository saleLineRepository;
    private final EnterpriseService enterpriseService;
    private final ArticleMapper articleMapper;
    private final SaleMapper saleMapper;
    private final CustomerOrderLineMapper customerOrderLineMapper;
    private final ProviderMapper providerMapper;
    private final ObjectValidator validator;


    @Override
    public ArticleDto saveArticle(ArticleRequest articleRequest) {
        validator.validate(articleRequest);

        var category = this.getCategory(articleRequest.codeCategory());
        var enterprise = enterpriseService.findById(articleRequest.enterpriseId());
        var article = articleMapper.fromArticle(articleRequest, category, enterprise);
        article.setCodeArticle(generateRandomCode(8));

        log.info("Article gonna be save with successfully");

        return articleMapper.fromArticleDto(articleRepository.save(article));
    }

    @Override
    public ArticleDto updateArticle(ArticleRequest articleRequest, Long id) {
        var article = this.findById(id);
        validator.validate(articleRequest);

        var category = this.getCategory(articleRequest.codeCategory());

        article.setDesignation(articleRequest.designation());
        article.setRateTax(articleRequest.rateTax());
        article.setUnitPriceHt(articleRequest.unitPriceHt());
        article.setUnitPriceTtc(articleRequest.unitPriceTtc());
        article.setLastModifiedTime(Instant.now());
        article.setCategory(category);
        article.setPicture(articleRequest.picture());

        return articleMapper.fromArticleDto(articleRepository.save(article));
    }

    public Article findById (Long id) {
        return articleRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Nothing article with ID ="+id+"has been found in DataBase")
        );
    }

    @Override
    public ArticleDto getArticle(Long id) {
      return articleMapper.fromArticleDto(this.findById(id));
    }

    @Override
    public ArticleDto getCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle))
            throw new EntityNotFoundException("Nothing Article with CODE ="+codeArticle+ "has been found in DataBase");

       return articleMapper.fromArticleDto(articleRepository.findByCodeArticle(codeArticle));
    }

    @Override
    public List<ArticleDto> listArticle() {
        return articleRepository.findAll().stream()
                .map(articleMapper::fromArticleDto)
                .toList();
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {
        return articleRepository.findAllByCategoryId(categoryId).stream()
                .map(articleMapper::fromArticleDto)
                .toList();
    }

    @Override
    public List<SaleLineDto> findHistorySales(Long articleId) {
        return saleLineRepository.findAllByArticleId(articleId).stream()
                .map(saleMapper::fromSaleLine)
                .toList();
    }

    @Override
    public List<CustomerOrderLineDto> findHistoryCustomerOrder(Long articleId) {
        return customerOrderLineRepository.findAllByArticleId(articleId)
                .stream()
                .map(customerOrderLineMapper::fromCustomerOrderLineDto)
                .toList();
    }

    @Override
    public List<ProviderOrderLineDto> findHistoryProviderOrder(Long articleId) {
        return providerOrderLineRepository.findAllByArticleId(articleId).stream()
                .map(providerMapper::fromProviderOrderLine)
                .toList();
    }

    @Override
    public void deleteArticle(Long id) {
        if(id == null) {
            log.error("Article ID is null");
            return;
        }

        List<CustomerOrderLine> customerOrderLineList = customerOrderLineRepository.findAllByArticleId(id);
        if(!customerOrderLineList.isEmpty()){
            throw new InvalidOperationException(DELETE_ARTICLE_CUSTOMER_ORDER);
        }

        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByArticleId(id);
        if(!providerOrderLineList.isEmpty()){
            throw new InvalidOperationException(DELETE_ARTICLE_PROVIDER_ORDER);
        }

        List<SaleLine> saleLineList = saleLineRepository.findAllByArticleId(id);
        if(!saleLineList.isEmpty()){
            throw new InvalidOperationException(DELETE_ARTICLE_SALE);
        }

        articleRepository.deleteById(id);
    }

    private Category getCategory (String code) {
        var category = categoryRepository.findByCodeCategory(code);
        if(category == null){
            throw new BusinessException("Invalid category");
        }

        return category;
    }
}
