package com.inventor.management.inventor_management.stockMovement.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.service.ArticleService;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;
import com.inventor.management.inventor_management.stockMovement.entity.StockMovement;
import com.inventor.management.inventor_management.stockMovement.mapper.StockMapper;
import com.inventor.management.inventor_management.stockMovement.repository.StockMovementRepository;
import com.inventor.management.inventor_management.core.enums.TypeMoveStock;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final ArticleService articleService;
    private final StockMapper stockMapper;
    private final ObjectValidator validator;

   @Override
    public BigDecimal stockRealArticle(Long articleId) {
        if(articleId == null) {
            log.warn("ID article is NULL");
            return BigDecimal.valueOf(-1);
        }
        articleService.getArticle(articleId);

        return stockMovementRepository.stockRealArticle(articleId);
    }

    @Override
    public List<StockMovementDto> listStockMovementArticle(Long articleId) {
        return stockMovementRepository.findAllByArticleId(articleId)
                .stream()
                .map(stockMapper::fromStockMovement)
                .collect(Collectors.toList());
    }

    private StockMovementDto entrancePositive (StockMovementRequest stockMovement, TypeMoveStock typeMoveStock){
        validator.validate(stockMovement);
        var article = this.getArticle(stockMovement.getArticleId());

        stockMovement.setQuantity(BigDecimal.valueOf(Math.abs(stockMovement.getQuantity().doubleValue())));
        stockMovement.setTypeMoveStock(typeMoveStock);
        StockMovement movement = stockMovementRepository.save(
                stockMapper.fromStockMovementDto(stockMovement, article)
        );

        return stockMapper.fromStockMovement(movement);
    }

    private StockMovementDto exitNegative (StockMovementRequest stockMovement, TypeMoveStock typeMoveStock){
        validator.validate(stockMovement);
        var article = this.getArticle(stockMovement.getArticleId());

        stockMovement.setQuantity(BigDecimal.valueOf(Math.abs(stockMovement.getQuantity().doubleValue() * -1)));
        stockMovement.setTypeMoveStock(typeMoveStock);
        StockMovement movement = stockMovementRepository.save(
                stockMapper.fromStockMovementDto(stockMovement, article)
        );

        return stockMapper.fromStockMovement(movement);

    }

    @Override
    public StockMovementDto entranceStock(StockMovementRequest stockMovement) {
        return entrancePositive(stockMovement, TypeMoveStock.ENTRANCE);
    }

    @Override
    public StockMovementDto exitStock(StockMovementRequest stockMovement) {
        return exitNegative(stockMovement,TypeMoveStock.EXIT);
    }

    @Override
    public StockMovementDto correctionStockPositive(StockMovementRequest stockMovement) {
        return entrancePositive(stockMovement,TypeMoveStock.CORRECTION_POSITIVE);
    }

    @Override
    public StockMovementDto correctionStockNegative(StockMovementRequest stockMovement) {
        return exitNegative(stockMovement,TypeMoveStock.CORRECTION_NEGATIVE);
    }

    private Article getArticle(Long id) {
       return articleService.findById(id);
    }
}
