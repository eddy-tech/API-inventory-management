package com.inventor.management.inventor_management.stockMovement.resource.restcontroller;

import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.stockMovement.resource.api.StockMovementApi;
import com.inventor.management.inventor_management.stockMovement.roots.StockMovementEndPoint;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(StockMovementEndPoint.STOCK_MOVEMENT_ENDPOINT)
public class StockMovementRestController implements StockMovementApi {
    private final StockMovementService stockMovementService;

    @Override
    public BigDecimal stockRealArticle(Long articleId) {
        return stockMovementService.stockRealArticle(articleId);
    }
    @Override
    public List<StockMovementDto> listStockMovementArticle(Long articleId) {
        return stockMovementService.listStockMovementArticle(articleId);
    }
    @Override
    public StockMovementDto entranceStock(StockMovementDto stockMovement) {
        return stockMovementService.entranceStock(stockMovement);
    }
    @Override
    public StockMovementDto exitStock(StockMovementDto stockMovement) {
        return stockMovementService.exitStock(stockMovement);
    }
    @Override
    public StockMovementDto correctionStockPositive(StockMovementDto stockMovement) {
        return stockMovementService.correctionStockPositive(stockMovement);
    }
    @Override
    public StockMovementDto correctionStockNegative(StockMovementDto stockMovement) {
        return stockMovementService.correctionStockNegative(stockMovement);
    }
}
