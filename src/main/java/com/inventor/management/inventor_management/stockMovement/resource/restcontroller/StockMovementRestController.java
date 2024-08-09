package com.inventor.management.inventor_management.stockMovement.resource.restcontroller;

import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.stockMovement.resource.api.StockMovementApi;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.inventor.management.inventor_management.stockMovement.roots.StockMovementEndPoint.STOCK_MOVEMENT_ENDPOINT;

@RestController
@RequiredArgsConstructor
@RequestMapping(STOCK_MOVEMENT_ENDPOINT)
public class StockMovementRestController implements StockMovementApi {
    private final StockMovementService stockMovementService;

    @Override
    public ResponseEntity<BigDecimal> stockRealArticle(Long articleId) {
        return ResponseEntity.ok(stockMovementService.stockRealArticle(articleId));
    }
    @Override
    public ResponseEntity<List<StockMovementDto>> listStockMovementArticle(Long articleId) {
        return ResponseEntity.ok(stockMovementService.listStockMovementArticle(articleId));
    }
    @Override
    public ResponseEntity<StockMovementDto> entranceStock(StockMovementDto stockMovement) {
        return ResponseEntity.ok(stockMovementService.entranceStock(stockMovement));
    }
    @Override
    public ResponseEntity<StockMovementDto> exitStock(StockMovementDto stockMovement) {
        return ResponseEntity.ok(stockMovementService.exitStock(stockMovement));
    }
    @Override
    public ResponseEntity<StockMovementDto> correctionStockPositive(StockMovementDto stockMovement) {
        return ResponseEntity.ok(stockMovementService.correctionStockPositive(stockMovement));
    }
    @Override
    public ResponseEntity<StockMovementDto> correctionStockNegative(StockMovementDto stockMovement) {
        return ResponseEntity.ok(stockMovementService.correctionStockNegative(stockMovement));
    }
}
