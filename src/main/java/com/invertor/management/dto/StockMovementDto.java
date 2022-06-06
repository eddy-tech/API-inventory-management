package com.invertor.management.dto;

import com.invertor.management.enums.TypeMoveStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class StockMovementDto {

    private Long id;

    private Instant dateMovement;

    private BigDecimal quantity;

    private TypeMoveStock typeMoveStock;

    private ArticleDto articleDto;
}
