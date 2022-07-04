package com.inventor.management.services.impl;

import com.inventor.management.dto.StockMovementDto;
import com.inventor.management.entities.StockMovement;
import com.inventor.management.enums.TypeMoveStock;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.StockMovementRepository;
import com.inventor.management.services.interfaces.ArticleService;
import com.inventor.management.services.interfaces.StockMovementService;
import com.inventor.management.validators.StockMovementValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private StockMovementRepository stockMovementRepository;
    private ArticleService articleService;
    private StockMapperImpl dtoMapper;
    private StockMovementService stockMovementService;

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
        return stockMovementRepository.findAllByArticleId(articleId).stream()
                .map(stockMovement -> dtoMapper.fromStockMovement(stockMovement))
                .collect(Collectors.toList());
    }

    private void validateStockMovement (StockMovementDto stockMovement){
        List<String> errors = StockMovementValidator.validate(stockMovement);
        if(!errors.isEmpty()){
            log.error("Article is not valid",stockMovement);
            throw new InvalidEntityException("Stock movement is invalid", ErrorCodes.STOCK_MOVEMENT_NOT_VALID);
        }
    }

    private StockMovementDto entrancePositive (StockMovementDto stockMovement, TypeMoveStock typeMoveStock){
        validateStockMovement(stockMovement);
        stockMovement.setQuantity(BigDecimal.valueOf(Math.abs(stockMovement.getQuantity().doubleValue())));
        stockMovement.setTypeMoveStock(typeMoveStock);
        StockMovement movement = stockMovementRepository.save(dtoMapper.fromStockMovementDto(stockMovement));

        return dtoMapper.fromStockMovement(movement);
    }

    private StockMovementDto exitNegative (StockMovementDto stockMovement, TypeMoveStock typeMoveStock){
        validateStockMovement(stockMovement);
        stockMovement.setQuantity(BigDecimal.valueOf(Math.abs(stockMovement.getQuantity().doubleValue() * -1)));
        stockMovement.setTypeMoveStock(typeMoveStock);
        StockMovement movement = stockMovementRepository.save(dtoMapper.fromStockMovementDto(stockMovement));

        return dtoMapper.fromStockMovement(movement);

    }

    @Override
    public StockMovementDto entranceStock(StockMovementDto stockMovement) {
        return entrancePositive(stockMovement, TypeMoveStock.ENTRANCE);
    }

    @Override
    public StockMovementDto exitStock(StockMovementDto stockMovement) {
        return exitNegative(stockMovement,TypeMoveStock.EXIT);
    }

    @Override
    public StockMovementDto correctionStockPositive(StockMovementDto stockMovement) {
        return entrancePositive(stockMovement,TypeMoveStock.CORRECTION_POSITIVE);
    }

    @Override
    public StockMovementDto correctionStockNegative(StockMovementDto stockMovement) {
        return exitNegative(stockMovement,TypeMoveStock.CORRECTION_NEGATIVE);
    }
}
