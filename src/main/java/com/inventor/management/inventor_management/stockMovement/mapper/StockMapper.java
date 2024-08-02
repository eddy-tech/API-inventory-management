package com.inventor.management.inventor_management.stockMovement.mapper;

import com.inventor.management.inventor_management.stockMovement.entity.StockMovement;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StockMapper {
    public StockMovementDto fromStockMovement (StockMovement stockMovement){
         StockMovementDto stockMovementDto = new StockMovementDto();
         BeanUtils.copyProperties(stockMovement,stockMovementDto);
         return stockMovementDto;
    }

    public StockMovement fromStockMovementDto (StockMovementDto stockMovementDto){
         StockMovement stockMovement = new StockMovement();
         BeanUtils.copyProperties(stockMovementDto,stockMovement);
         return stockMovement;
    }
}
