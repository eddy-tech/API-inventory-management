package com.inventor.management.inventor_management.stockMovement.dto;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.core.enums.SourceStockMovement;
import com.inventor.management.core.enums.TypeMoveStock;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class StockMovementDto {
    private Long id;
    private Instant dateMovement;
    private BigDecimal quantity;
    private TypeMoveStock typeMoveStock;
    private SourceStockMovement sourceStockMovement;
    private Long id_enterprise;
    private ArticleDto articleDto;
}
