package com.inventor.management.inventor_management.sale.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.sale.entity.Sale;
import com.inventor.management.inventor_management.sale.mapper.SaleMapper;
import com.inventor.management.inventor_management.sale.service.SaleService;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import com.inventor.management.inventor_management.core.enums.SourceStockMovement;
import com.inventor.management.inventor_management.core.enums.TypeMoveStock;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.saleLine.repository.SaleLineRepository;
import com.inventor.management.inventor_management.sale.repository.SaleRepository;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {
    private final ArticleRepository articleRepository;
    private final SaleRepository saleRepository;
    private final SaleLineRepository saleLineRepository;
    private final StockMovementService stockMovementService;
    private final SaleMapper saleMapper;
    private final ArticleMapper articleMapper;
    private final ObjectValidator validator;


    @Override
    public SaleDto saveSale(SaleDto saleDto) {
        validator.validate(saleDto);
        List<String> articleError = new ArrayList<>();

        saleDto.getSaleLines().forEach(saleLine -> {
            Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
            if(article.isEmpty()){
                articleError.add("Nothing article with ID ="+saleLine.getArticle().getId()+"was found in database");
            }
        });

        if(!articleError.isEmpty()){
            log.error("One or more articles were not found in the database");
            throw new InvalidEntityException("One or more articles were not found in database");
        }

        Sale savedSale = saleRepository.save(saleMapper.fromSaleDto(saleDto));

        saleDto.getSaleLines().forEach(saleLine -> {
            SaleLine saleLines = saleMapper.fromSaleLineDto(saleLine);
            saleLines.setSale(savedSale);
            saleLineRepository.save(saleLines);
            updateStockMovementSale(saleLine); // METTRE A JOUR STOCK DE VENTE
        });

        return saleMapper.fromSale(savedSale);
    }

    @Override
    public SaleDto updateSale(SaleDto saleDto) {
        validator.validate(saleDto);
        List<String> articleErrors = new ArrayList<>();

        if(saleDto.getSaleLines()!= null){
            saleDto.getSaleLines().forEach(saleLine -> {
                if(saleLine.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Nothing article with ID ="+saleLine.getArticle().getId()+"was found in database");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.error("One or more articles were not found in database");
            throw new InvalidEntityException("One or more articles were not found in database", articleErrors);
        }

        Sale updatedSale = saleRepository.save(saleMapper.fromSaleDto(saleDto));

        if(saleDto.getSaleLines() != null){
            saleDto.getSaleLines().forEach(saleLine -> {
                SaleLine saveSaleLine = saleMapper.fromSaleLineDto(saleLine);
                saveSaleLine.setSale(updatedSale);
                saleLineRepository.save(saveSaleLine);
            });
        }
        return saleMapper.fromSale(updatedSale);
    }

    @Override
    public SaleDto getSale(Long id) {
        if(id == null){
            log.error("Id Sale is NULL");
            return null;
        }
        var sale = saleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Sale was found with ID ="+id+"in database"));

        return saleMapper.fromSale(sale);
    }

    @Override
    public SaleDto getCodeSale(String codeSale) {
        if(!StringUtils.hasLength(codeSale)){
            log.error("code Sale is invalid");
            throw new EntityNotFoundException(
                    "Nothing code sale with ID ="+codeSale+"was found in database"
            );
        }

        return saleMapper.fromSale(saleRepository.findByCodeSale(codeSale));
    }

    @Override
    public List<SaleDto> listSale() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::fromSale)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteSale(Long id) {
        if(id == null){
            log.error("Sale ID is NULL");
            return;
        }

        var articleList = articleRepository.findAllByCategoryId(id);
        if(!articleList.isEmpty()){
            throw new InvalidOperationException("Unable to delete sale that has already using article");
        }

        var saleLineList = saleLineRepository.findAllBySaleId(id);
        if(!saleLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete sale that has already using sale line");
        }

        saleRepository.deleteById(id);
    }

    private void updateStockMovementSale (SaleLine saleLine){
            var stock = new StockMovementDto();
            stock.setArticleDto(articleMapper.fromArticleDto(saleLine.getArticle()));
            stock.setDateMovement(Instant.now());
            stock.setTypeMoveStock(TypeMoveStock.EXIT);
            stock.setQuantity(saleLine.getQuantity());
            stock.setSourceStockMovement(SourceStockMovement.SALE);
            stock.setId_enterprise(saleLine.getId_enterprise());

            stockMovementService.exitStock(stock);
    }
}
