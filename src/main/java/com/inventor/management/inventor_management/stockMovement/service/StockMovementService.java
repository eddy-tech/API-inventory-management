package com.inventor.management.inventor_management.stockMovement.service;

import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;

import java.math.BigDecimal;
import java.util.List;

public interface StockMovementService {
    BigDecimal stockRealArticle (Long articleId);
    List<StockMovementDto> listStockMovementArticle (Long articleId);
    StockMovementDto entranceStock (StockMovementRequest stockMovement);
    StockMovementDto exitStock (StockMovementRequest stockMovement);
    StockMovementDto correctionStockPositive (StockMovementRequest stockMovement);
    StockMovementDto correctionStockNegative (StockMovementRequest stockMovement);
}
