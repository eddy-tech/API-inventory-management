package com.inventor.management.services.interfaces;

import com.inventor.management.dto.StockMovementDto;
import com.inventor.management.entities.StockMovement;

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
