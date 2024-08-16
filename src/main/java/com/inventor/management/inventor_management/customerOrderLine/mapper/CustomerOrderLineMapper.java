package com.inventor.management.inventor_management.customerOrderLine.mapper;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.mapper.ArticleMapper;
import com.inventor.management.inventor_management.customerOrder.entity.CustomerOrder;
import com.inventor.management.inventor_management.customerOrder.mapper.CustomerOrderMapper;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.customerOrderLine.entity.CustomerOrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CustomerOrderLineMapper {
    private final ArticleMapper articleMapper;
    private final CustomerOrderMapper customerOrderMapper;
    public CustomerOrderLine fromCustomerOrderLine (
            CustomerOrderLineDto customerOrderLineDto, Article article, CustomerOrder customerOrder
    ){
        return CustomerOrderLine.builder()
                .unitPrice(customerOrderLineDto.getUnitPrice())
                .quantity(customerOrderLineDto.getQuantity())
                .article(article)
                .customerOrder(customerOrder)
                .creationTime(Instant.now())
                .build();
    }

    public CustomerOrderLineDto fromCustomerOrderLineDto (CustomerOrderLine customerOrderLine){
        return CustomerOrderLineDto.builder()
                .unitPrice(customerOrderLine.getUnitPrice())
                .quantity(customerOrderLine.getQuantity())
                .articleDto(articleMapper.fromArticleDto(customerOrderLine.getArticle()))
                .customerOrder(customerOrderMapper.fromCustomerOrder(customerOrderLine.getCustomerOrder()))
                .build();
    }
}
