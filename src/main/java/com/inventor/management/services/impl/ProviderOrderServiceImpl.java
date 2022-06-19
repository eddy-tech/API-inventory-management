package com.inventor.management.services.impl;

import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.entities.Article;
import com.inventor.management.entities.Provider;
import com.inventor.management.entities.ProviderOrder;
import com.inventor.management.entities.ProviderOrderLine;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.ProviderOrderLineRepository;
import com.inventor.management.repository.ProviderOrderRepository;
import com.inventor.management.repository.ProviderRepository;
import com.inventor.management.services.interfaces.ProviderOrderService;
import com.inventor.management.validators.ProviderOrderValidator;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProviderOrderServiceImpl implements ProviderOrderService {

    private ProviderOrderRepository providerOrderRepository;
    private ArticleRepository articleRepository;
    private ProviderRepository providerRepository;
    private ProviderOrderLineRepository providerOrderLineRepository;
    private StockMapperImpl dtoMapper;


    @Override
    public ProviderOrderDto saveProviderOrder(ProviderOrderDto providerOrderDto) {
        List<String> errors = ProviderOrderValidator.validate(providerOrderDto);
        if(!errors.isEmpty()){
            log.error("Provider Order is invalid");
            throw new InvalidEntityException("Provider Order is invalid", ErrorCodes.PROVIDER_ORDER_NOT_VALID);
        }

        Provider provider = providerRepository.findById(providerOrderDto.getProviderDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="+providerOrderDto.getProviderDto().getId()+"was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND));

        List<String> articleErrors = new ArrayList<>();

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                if(providerOrderLineDto.getArticleDto() != null){
                    Optional<Article> articleDto = articleRepository.findById(providerOrderLineDto.getArticleDto().getId());
                    if(articleDto.isEmpty()){
                        articleErrors.add("Article with ID ="+providerOrderLineDto.getArticleDto().getId()+"not exist in database");
                    } else {
                        articleErrors.add("Impossible to save provider order with an article null");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in database",ErrorCodes.ARTICLES_NOT_FOUND,articleErrors);
        }

        ProviderOrder providerOrder = dtoMapper.fromProviderOrderDto(providerOrderDto);
        ProviderOrder savedProviderOrder = providerOrderRepository.save(providerOrder);

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                ProviderOrderLine providerOrderLine = dtoMapper.fromProviderOrderLineDto(providerOrderLineDto);
                providerOrderLine.setProviderOrder(savedProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return dtoMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateProviderOrder(ProviderOrderDto providerOrderDto) {
        List<String> errors = ProviderOrderValidator.validate(providerOrderDto);
        if(!errors.isEmpty()){
            log.error("Provider order is invalid",providerOrderDto);
            throw new InvalidEntityException("Provider order is invalid",ErrorCodes.PROVIDER_ORDER_NOT_FOUND,errors);
        }

        Provider provider = providerRepository.findById(providerOrderDto.getProviderDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="+providerOrderDto.getProviderDto().getId()+"was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND));

        List<String> articleErrors = new ArrayList<>();

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                if(providerOrderLineDto.getArticleDto() != null){
                    Optional<Article> article = articleRepository.findById(providerOrderLineDto.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Article with ID ="+providerOrderLineDto.getArticleDto().getId()+"was not exit in database");
                    } else {
                        articleErrors.add("Impossible to save Provider with an article NULL");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new EntityNotFoundException("Article not exist in database",ErrorCodes.ARTICLES_NOT_FOUND);
        }

        ProviderOrder updateProviderOrder = providerOrderRepository.save(dtoMapper.fromProviderOrderDto(providerOrderDto));

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                ProviderOrderLine providerOrderLine = dtoMapper.fromProviderOrderLineDto(providerOrderLineDto);
                providerOrderLine.setProviderOrder(updateProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return dtoMapper.fromProviderOrder(updateProviderOrder);
    }

    @Override
    public ProviderOrderDto getProviderOrder(Long id) {
        if(id == null){
            log.error("Provider Order ID is NULL");
            return null;
        }

        ProviderOrder providerOrder = providerOrderRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Provider order with ID="+ id +"was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND));
        return dtoMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public ProviderOrderDto getCodeProviderOrder(String codeProviderOrder) {
        if(!StringUtils.hasLength(codeProviderOrder)){
            log.error("Provider Order is NULL");
            throw new InvalidEntityException("Nothing provider order with code ="+codeProviderOrder+"was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND);
        }

        ProviderOrder providerOrder = providerOrderRepository.findProviderOrderByCodeProviderOrder(codeProviderOrder);
        return dtoMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public List<ProviderOrderDto> listProviderOrder() {
        List<ProviderOrder> providerOrdersList = providerOrderRepository.findAll();
        List<ProviderOrderDto> providerOrderDtoList = providerOrdersList.stream()
                .map(providerOrder -> dtoMapper.fromProviderOrder(providerOrder)).collect(Collectors.toList());

        return providerOrderDtoList;
    }

    @Override
    public void deleteProviderOrder(Long id) {
        if(id == null){
            log.error("Provider order ID is not exist");
            return;
        }

        providerOrderRepository.deleteById(id);

    }
}
