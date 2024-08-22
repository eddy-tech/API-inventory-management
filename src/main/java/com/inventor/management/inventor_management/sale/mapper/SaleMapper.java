package com.inventor.management.inventor_management.sale.mapper;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.sale.dto.SaleRequest;
import com.inventor.management.inventor_management.sale.entity.Sale;
import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineRequest;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SaleMapper {
    private final ArticleMapper articleMapper;
        public Sale fromSaleRequest (SaleRequest saleRequest){
        return Sale.builder()
                .dateSale(saleRequest.dateSale())
                .comments(saleRequest.comments())
                .creationTime(Instant.now())
                .build();
    }

    public SaleDto fromSale (Sale sale){
        return SaleDto.builder()
                .id(sale.getId())
                .codeSale(sale.getCodeSale())
                .dateSale(sale.getDateSale())
                .comments(sale.getComments())
                .build();
    }

    public SaleLineDto fromSaleLine (SaleLine saleLine){
        return SaleLineDto.builder()
                .id(saleLine.getId())
                .unitPrice(saleLine.getUnitPrice())
                .quantity(saleLine.getQuantity())
                .articleDto(articleMapper.fromArticleDto(saleLine.getArticle()))
                .saleDto(this.fromSale(saleLine.getSale()))
                .build();
    }

    public SaleLine fromSaleLineRequest(SaleLineRequest saleLineRequest, Article article, Sale sale) {
            return SaleLine.builder()
                   .unitPrice(saleLineRequest.getUnitPrice())
                   .quantity(saleLineRequest.getQuantity())
                   .article(article)
                   .sale(sale)
                   .creationTime(Instant.now())
                   .build();
    }
}
