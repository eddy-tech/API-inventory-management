package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.StockMovementDto;
import com.inventor.management.services.interfaces.StockMovementService;
import com.inventor.management.web.api.StockMovementApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
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
