package com.inventor.management.dto;

import com.inventor.management.enums.SourceStockMovement;
import com.inventor.management.enums.TypeMoveStock;
import lombok.Data;

import javax.persistence.Column;
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
