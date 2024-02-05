package com.inventor.management.mapper;

import com.inventor.management.dto.*;
import com.inventor.management.entities.*;
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
