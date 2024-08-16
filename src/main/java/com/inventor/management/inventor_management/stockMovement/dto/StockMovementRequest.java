package com.inventor.management.inventor_management.stockMovement.dto;

import com.inventor.management.inventor_management.core.enums.SourceStockMovement;
import com.inventor.management.inventor_management.core.enums.TypeMoveStock;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Data
public class StockMovementRequest {
        @NotNull(message = "movement date is required")
        private Instant dateMovement;
        @NotNull(message = "quantity is required")
        private BigDecimal quantity;
        @NotNull(message = "move stock type is required")
        private TypeMoveStock typeMoveStock;
        @NotNull(message = "source stock movement is required")
        private SourceStockMovement sourceStockMovement;
        private Long articleId;
}
