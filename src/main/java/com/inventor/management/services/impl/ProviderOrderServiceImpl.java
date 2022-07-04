package com.inventor.management.services.impl;

import com.inventor.management.dto.*;
import com.inventor.management.entities.*;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.ProviderOrderLineRepository;
import com.inventor.management.repository.ProviderOrderRepository;
import com.inventor.management.repository.ProviderRepository;
import com.inventor.management.services.interfaces.ProviderOrderService;
import com.inventor.management.validators.ArticleValidator;
import com.inventor.management.validators.ProviderOrderValidator;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.mapper.StockMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProviderOrderServiceImpl implements ProviderOrderService {

    private ProviderOrderRepository providerOrderRepository;
    private ArticleRepository articleRepository;
    private ProviderRepository providerRepository;
    private ProviderOrderLineRepository providerOrderLineRepository;
    private StockMapperImpl dtoMapper;

    @Autowired
    public ProviderOrderServiceImpl(ProviderOrderRepository providerOrderRepository, ArticleRepository articleRepository,
                                    ProviderRepository providerRepository, ProviderOrderLineRepository providerOrderLineRepository,
                                    StockMapperImpl dtoMapper) {
        this.providerOrderRepository = providerOrderRepository;
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
        this.providerOrderLineRepository = providerOrderLineRepository;
        this.dtoMapper = dtoMapper;
    }

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

    private void checkIdOrder (Long orderId){
        if(orderId == null) {
            log.error("provider order ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID",
                    ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }
    }

    private void checkIdOrderLine (Long orderLineId) {
        if(orderLineId == null) {
            log.error("provider order Line ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null order line",
                    ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }
    }

    private void checkIdArticle (Long idArticle, String message){
        if(idArticle == null){
            log.error("ID of"+message+"is NULL");
            throw new InvalidOperationException("Unable to edit state order with a" + message + "article ID null",
                    ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }
    }

    private ProviderOrderDto checkStateOrder(Long orderId){
        ProviderOrderDto providerOrder = getProviderOrder(orderId);
        if(providerOrder.isOrderDelivered()) throw new InvalidOperationException("Unable to edit state order with null ID",
                ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);

        return providerOrder;
    }

    private Optional<ProviderOrderLine> findCProviderOrderLine (Long orderLineId){
        Optional<ProviderOrderLine> providerOrderLineOptional = providerOrderLineRepository.findById(orderLineId);

        // CHECKED IF LINE COMMAND EXIST WITH ID PROVIDED
        if(providerOrderLineOptional.isEmpty())
            throw new EntityNotFoundException("Nothing customer order line has been found with ID ="+orderLineId,
                    ErrorCodes.CUSTOMER_NOT_FOUND);

        return providerOrderLineOptional;
    }

    @Override
    public ProviderOrderDto updateStateOrder(Long orderId, StateOrder stateOrder) {
        checkIdOrder(orderId);

        if(!StringUtils.hasLength(String.valueOf(stateOrder))) {
            log.error("provider state order is NULL");
            throw new InvalidOperationException("Unable to edit state order with state NULL", ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }

        ProviderOrderDto orderDto = checkStateOrder(orderId);
        orderDto.setStateOrder(stateOrder);
        ProviderOrder providerOrder = dtoMapper.fromProviderOrderDto(orderDto);
        ProviderOrder savedProviderOrder = providerOrderRepository.save(providerOrder);

        return dtoMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);
        if(quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantity of customer order is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null quantity or 0", ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }

        ProviderOrderDto providerOrder = checkStateOrder(orderId);
        Optional<ProviderOrderLine> providerOrderLineOptional = findCProviderOrderLine(orderLineId);

        ProviderOrderLine providerOrderLine = providerOrderLineOptional.get();
        providerOrderLine.setQuantity(quantity);
        providerOrderLineRepository.save(providerOrderLine);

        return providerOrder;
    }

    @Override
    public ProviderOrderDto updateProvider(Long orderId, Long providerId) {
        checkIdOrder(orderId);

        if(providerId == null) {
            log.error("customer ID is null");
            throw new InvalidOperationException("Unable to edit state order with null ID",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }

        ProviderOrderDto providerOrder = checkStateOrder(orderId);

        Provider providerOptional = providerRepository.findById(providerId)
                .orElseThrow(()->new EntityNotFoundException("Nothing customer was found with ID ="+providerId,
                        ErrorCodes.CUSTOMER_NOT_FOUND));

        ProviderDto provider = dtoMapper.fromProvider(providerOptional);
        providerOrder.setProviderDto(provider);

        ProviderOrder savedProviderOrder = providerOrderRepository.save(dtoMapper.fromProviderOrderDto(providerOrder));
        return dtoMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateArticle(Long orderId, Long orderLineId, Long articleId) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);
        checkIdArticle(articleId, "new");

        ProviderOrderDto providerOrder = checkStateOrder(orderId); // CHECK STATE OF ORDER
        Optional<ProviderOrderLine> providerOrderLine = findCProviderOrderLine(orderLineId);

        Article articleOptional = articleRepository.findById(articleId)
                .orElseThrow(()->new EntityNotFoundException("Nothing article was found with ID ="+articleId,
                        ErrorCodes.ARTICLES_NOT_FOUND));

        List<String> errors = ArticleValidator.validate(dtoMapper.fromArticle(articleOptional));
        if(!errors.isEmpty()) throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID,errors);

        ProviderOrderLine providerOrderLineToSaved = providerOrderLine.get();
        providerOrderLineToSaved.setArticle(articleOptional);
        providerOrderLineRepository.save(providerOrderLineToSaved);

        return providerOrder;
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
    public List<ProviderOrderLineDto> findAllProviderOrdersLinesByProviderOrderId(Long orderId) {
        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(orderId);
        List<ProviderOrderLineDto> providerOrderLineDtoList = providerOrderLineList.stream()
                .map(providerOrderLineDto -> dtoMapper.fromProviderOrderLine(providerOrderLineDto))
                .collect(Collectors.toList());

        return providerOrderLineDtoList;
    }

    @Override
    public void deleteProviderOrder(Long id) {
        if(id == null){
            log.error("Provider order ID is not exist");
            return;
        }

        providerOrderRepository.deleteById(id);

    }

    @Override
    public ProviderOrderDto deleteArticle(Long orderId, Long orderLineId) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);

        ProviderOrderDto providerOrder = checkStateOrder(orderId);
        // JUST TO CHECK CUSTOMER ORDER LINE AND INFORM THE CLIENT IN CASE IT IS ABSENT
        findCProviderOrderLine(orderLineId);

        providerOrderLineRepository.deleteById(orderLineId);
        return providerOrder;
    }
}
