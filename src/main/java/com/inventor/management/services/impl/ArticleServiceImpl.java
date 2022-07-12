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
import com.inventor.management.repository.CustomerOrderLineRepository;
import com.inventor.management.repository.ProviderOrderLineRepository;
import com.inventor.management.repository.SaleLineRepository;
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
    private CustomerOrderLineRepository customerOrderLineRepository;
    private ProviderOrderLineRepository providerOrderLineRepository;
    private SaleLineRepository saleLineRepository;
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
              new EntityNotFoundException("Nothing article with ID ="+id+"were found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND));

      return dtoMapper.fromArticle(article);
    }

    @Override
    public ArticleDto getCodeArticle(String codeArticle) {
        if(!StringUtils.hasLength(codeArticle))
            throw new EntityNotFoundException("Nothing Article with CODE ="+codeArticle+ "were found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND);
        Article article = articleRepository.findByCodeArticle(codeArticle);

       return dtoMapper.fromArticle(article);
    }

    @Override
    public List<ArticleDto> listArticle() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleDto> articleDtoList =articleList.stream()
                .map(article -> dtoMapper.fromArticle(article))
                .collect(Collectors.toList());

        return articleDtoList;
    }

    @Override
    public List<SaleLineDto> findHistorySales(Long articleId) {
        List<SaleLine> saleLineList = saleLineRepository.findAllByArticleId(articleId);
        List<SaleLineDto> saleLineDtoList = saleLineList.stream()
                .map(saleLine -> dtoMapper.fromSaleLine(saleLine))
                .collect(Collectors.toList());

        return saleLineDtoList;
    }

    @Override
    public List<CustomerOrderLineDto> findHistoryCustomerOrder(Long articleId) {
        return customerOrderLineRepository.findAllByArticleId(articleId).stream()
                .map(customerOrderLine -> dtoMapper.fromCustomerOrderLine(customerOrderLine))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderOrderLineDto> findHistoryProviderOrder(Long articleId) {
        return providerOrderLineRepository.findAllByArticleId(articleId).stream()
                .map(providerOrderLine -> dtoMapper.fromProviderOrderLine(providerOrderLine))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllArticleByCategory(Long categoryId) {
        return articleRepository.findAllByCategoryId(categoryId).stream()
                .map(article -> dtoMapper.fromArticle(article))
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
            throw new InvalidOperationException("Unable to delete an article that already use in customer order",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }

        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByArticleId(id);
        if(!providerOrderLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete an article that already use in provider order",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }

        List<SaleLine> saleLineList = saleLineRepository.findAllByArticleId(id);
        if(!saleLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete an article that already use in sale ",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }
     articleRepository.deleteById(id);
    }
}
