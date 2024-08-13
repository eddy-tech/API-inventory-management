package com.inventor.management.inventor_management.stockMovement.mapper;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;
import com.inventor.management.inventor_management.stockMovement.entity.StockMovement;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class StockMapper {
    private final ArticleMapper articleMapper;
    public StockMovementDto fromStockMovement (StockMovement stockMovement){
         return StockMovementDto.builder()
                 .id(stockMovement.getId())
                 .typeMoveStock(stockMovement.getTypeMoveStock())
                 .sourceStockMovement(stockMovement.getSourceStockMovement())
                 .dateMovement(stockMovement.getDateMovement())
                 .quantity(stockMovement.getQuantity())
                 .articleDto(articleMapper.fromArticleDto(stockMovement.getArticle()))
                 .build();
    }

    public StockMovement fromStockMovementDto (StockMovementRequest stockMovementRequest, Article article){
         return StockMovement.builder()
                 .dateMovement(stockMovementRequest.getDateMovement())
                 .sourceStockMovement(stockMovementRequest.getSourceStockMovement())
                 .typeMoveStock(stockMovementRequest.getTypeMoveStock())
                 .quantity(stockMovementRequest.getQuantity())
                 .article(article)
                 .creationTime(Instant.now())
                 .build();
    }
}
