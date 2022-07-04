package com.inventor.management.dto;

import com.inventor.management.enums.TypeMoveStock;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class StockMovementDto {

    private Long id;

    private Instant dateMovement;

    private BigDecimal quantity;

    private TypeMoveStock typeMoveStock;

    private Long id_enterprise;

    private ArticleDto articleDto;
}
