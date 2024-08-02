package com.inventor.management.stockMovement.service;

import com.inventor.management.stockMovement.dto.StockMovementDto;

import java.math.BigDecimal;
import java.util.List;

public interface StockMovementService {

    BigDecimal stockRealArticle (Long articleId);
    List<StockMovementDto> listStockMovementArticle (Long articleId);
    StockMovementDto entranceStock (StockMovementDto stockMovement);
    StockMovementDto exitStock (StockMovementDto stockMovement);
    StockMovementDto correctionStockPositive (StockMovementDto stockMovement);
    StockMovementDto correctionStockNegative (StockMovementDto stockMovement);
}
