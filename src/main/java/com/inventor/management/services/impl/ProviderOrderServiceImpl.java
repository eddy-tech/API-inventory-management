package com.inventor.management.services.impl;

import com.inventor.management.dto.*;
import com.inventor.management.entities.*;
import com.inventor.management.enums.SourceStockMovement;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.enums.TypeMoveStock;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.ArticleMapper;
import com.inventor.management.mapper.ProviderMapper;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.ProviderOrderLineRepository;
import com.inventor.management.repository.ProviderOrderRepository;
import com.inventor.management.repository.ProviderRepository;
import com.inventor.management.services.interfaces.ProviderOrderService;
import com.inventor.management.services.interfaces.StockMovementService;
import com.inventor.management.validators.ArticleValidator;
import com.inventor.management.validators.ProviderOrderValidator;
import com.inventor.management.exceptions.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProviderOrderServiceImpl implements ProviderOrderService {
    private final ProviderOrderRepository providerOrderRepository;
    private final ArticleRepository articleRepository;
    private final ProviderRepository providerRepository;
    private final ProviderOrderLineRepository providerOrderLineRepository;
    private final StockMovementService stockMovementService;
    private final ProviderMapper providerMapper;
    private final ArticleMapper articleMapper;

    private void checkIdOrder (Long orderId){
        if(orderId == null) {
            log.error("provider order ID is null");
            throw new InvalidOperationException(
                    "Unable to edit quantity ordered with null ID", ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE
            );
        }
    }

    private void checkIdOrderLine (Long orderLineId) {
        if(orderLineId == null) {
            log.error("provider order Line ID is null");
            throw new InvalidOperationException(
                    "Unable to edit quantity ordered with null order line", ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE
            );
        }
    }

    private void checkIdArticle (Long idArticle){
        if(idArticle == null){
            log.error("ID of"+"new"+"is NULL");
            throw new InvalidOperationException("Unable to edit state order with a" + "new" + "article ID null",
                    ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }
    }

    private ProviderOrderDto checkStateOrder(Long orderId){
        var providerOrder = getProviderOrder(orderId);
        if(providerOrder.isOrderDelivered()) throw new InvalidOperationException("Unable to edit state order with null ID",
                ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);

        return providerOrder;
    }

    private ProviderOrderLine findProviderOrderLine (Long orderLineId){
        return providerOrderLineRepository.findById(orderLineId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing customer order line has been found with ID ="+orderLineId,
                        ErrorCodes.CUSTOMER_NOT_FOUND)
                );
    }

    private Provider findProvider (Long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(()->new EntityNotFoundException("Nothing customer was found with ID ="+providerId,
                        ErrorCodes.PROVIDER_NOT_FOUND));
    }

    private Article findArticle (Long articleId){
        return  articleRepository.findById(articleId)
                .orElseThrow(()->new EntityNotFoundException("Nothing article was found with ID ="+articleId,
                        ErrorCodes.ARTICLES_NOT_FOUND));
    }

    private ProviderOrder findProviderOrder (Long providerOrderId){
        return providerOrderRepository.findById(providerOrderId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Provider order with ID="+ providerOrderId +"was found in database",
                        ErrorCodes.PROVIDER_ORDER_NOT_FOUND)
                );
    }

    @Override
    public ProviderOrderDto saveProviderOrder(ProviderOrderDto providerOrderDto) {
        List<String> errors = ProviderOrderValidator.validate(providerOrderDto);
        if(!errors.isEmpty()){
            log.error("Provider Order is invalid");
            throw new InvalidEntityException("Provider Order is invalid", ErrorCodes.PROVIDER_ORDER_NOT_VALID);
        }

        providerRepository.findById(providerOrderDto.getProviderDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="+providerOrderDto.getProviderDto().getId()+
                        "was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND));

        /*
        if(providerOrderDto.getId() != null && providerOrderDto.isOrderDelivered()){
            throw new InvalidOperationException("Unable to edit customer order when delivered",ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);
        }
         */

        List<String> articleErrors = new ArrayList<>();

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                if(providerOrderLineDto.getArticleDto() != null){
                    var articleDto = articleRepository.findById(providerOrderLineDto.getArticleDto().getId());
                    if(articleDto.isEmpty()){
                        articleErrors.add("Article with ID ="+providerOrderLineDto.getArticleDto().getId()+"not exist in database");
                    } else {
                        articleErrors.add("Unable to save provider order with an article null");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in database",ErrorCodes.ARTICLES_NOT_FOUND,articleErrors);
        }

        ProviderOrder providerOrder = providerMapper.fromProviderOrderDto(providerOrderDto);
        ProviderOrder savedProviderOrder = providerOrderRepository.save(providerOrder);

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                ProviderOrderLine providerOrderLine = providerMapper.fromProviderOrderLineDto(providerOrderLineDto);
                providerOrderLine.setProviderOrder(savedProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return providerMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateProviderOrder(ProviderOrderDto providerOrderDto) {
        List<String> errors = ProviderOrderValidator.validate(providerOrderDto);
        if(!errors.isEmpty()){
            log.error("Provider order is invalid" + providerOrderDto);
            throw new InvalidEntityException("Provider order is invalid",ErrorCodes.PROVIDER_ORDER_NOT_FOUND,errors);
        }

        Provider provider = providerRepository.findById(providerOrderDto.getProviderDto().getId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="+providerOrderDto.getProviderDto().getId()+
                        "was found in database",ErrorCodes.PROVIDER_ORDER_NOT_FOUND));

        if(providerOrderDto.getId() != null && providerOrderDto.isOrderDelivered())
            throw new InvalidOperationException("Unable to update provider order", ErrorCodes.PROVIDER_ORDER_NOT_MODIFIABLE);

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

        ProviderOrder updateProviderOrder = providerOrderRepository.save(providerMapper.fromProviderOrderDto(providerOrderDto));

        if(providerOrderDto.getProviderOrderLinesDto() != null){
            providerOrderDto.getProviderOrderLinesDto().forEach(providerOrderLineDto -> {
                ProviderOrderLine providerOrderLine = providerMapper.fromProviderOrderLineDto(providerOrderLineDto);
                providerOrderLine.setProviderOrder(updateProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return providerMapper.fromProviderOrder(updateProviderOrder);
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
        ProviderOrder providerOrder = providerMapper.fromProviderOrderDto(orderDto);
        ProviderOrder savedProviderOrder = providerOrderRepository.save(providerOrder);
        // MAKE THE STOCK OUT ONLY WHEN PROVIDER ORDER IS DELIVERED
        if(orderDto.isOrderDelivered()){
        updateStockMovementProvider(orderId); // METTRE A JOUR L'ETAT DE STOCK DU FOURNISSEUR
        }

        return providerMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);
        if(quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantity of customer order is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null quantity or 0",
                    ErrorCodes.CUSTOMER_ORDER_NOT_MODIFIABLE);
        }

        ProviderOrderDto providerOrder = checkStateOrder(orderId);
        ProviderOrderLine providerOrderLine = findProviderOrderLine(orderLineId);
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
        Provider providerOptional = findProvider(providerId);
        ProviderDto provider = providerMapper.fromProvider(providerOptional);
        providerOrder.setProviderDto(provider);

        ProviderOrder savedProviderOrder = providerOrderRepository.save(providerMapper.fromProviderOrderDto(providerOrder));
        return providerMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateArticle(Long orderId, Long orderLineId, Long articleId) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);
        checkIdArticle(articleId);

        ProviderOrderDto providerOrder = checkStateOrder(orderId); // CHECK STATE OF ORDER
        ProviderOrderLine providerOrderLine = findProviderOrderLine(orderLineId);
        Article articleOptional = findArticle(articleId);

        List<String> errors = ArticleValidator.validate(articleMapper.fromArticle(articleOptional));
        if(!errors.isEmpty()) throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID,errors);

        providerOrderLine.setArticle(articleOptional);
        providerOrderLineRepository.save(providerOrderLine);

        return providerOrder;
    }

    @Override
    public ProviderOrderDto getProviderOrder(Long id) {
        if(id == null){
            log.error("Provider Order ID is NULL");
            return null;
        }

        ProviderOrder providerOrder = findProviderOrder(id);
        return providerMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public ProviderOrderDto getCodeProviderOrder(String codeProviderOrder) {
        if(!StringUtils.hasLength(codeProviderOrder)){
            log.error("Provider Order is NULL");
            throw new InvalidEntityException("Nothing provider order with code ="+codeProviderOrder+"was found in database",
                    ErrorCodes.PROVIDER_ORDER_NOT_FOUND);
        }

        ProviderOrder providerOrder = providerOrderRepository.findByCodeProviderOrder(codeProviderOrder);
        return providerMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public List<ProviderOrderDto> listProviderOrder() {
        List<ProviderOrder> providerOrdersList = providerOrderRepository.findAll();

        return providerOrdersList.stream()
                .map(providerMapper::fromProviderOrder)
                    .collect(Collectors.toList());
    }

    @Override
    public List<ProviderOrderLineDto> findAllProviderOrdersLinesByProviderOrderId(Long orderId) {
        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(orderId);

        return providerOrderLineList.stream()
                .map(providerMapper::fromProviderOrderLine)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteProviderOrder(Long id) {
        if(id == null){
            log.error("Provider order ID is not exist");
            return;
        }

        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(id);
        if(!providerOrderLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete provider order that has already provider order line",
                    ErrorCodes.PROVIDER_ORDER_ALREADY_IN_USE);
        }

        providerOrderRepository.deleteById(id);

    }

    @Override
    public ProviderOrderDto deleteArticle(Long orderId, Long orderLineId) {
        checkIdOrder(orderId);
        checkIdOrderLine(orderLineId);
        ProviderOrderDto providerOrder = checkStateOrder(orderId);
        findProviderOrderLine(orderLineId); // JUST TO CHECK CUSTOMER ORDER LINE AND INFORM THE CLIENT IN CASE IT IS ABSENT
        providerOrderLineRepository.deleteById(orderLineId);

        return providerOrder;
    }

    public void updateStockMovementProvider (Long orderId){
        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(orderId);

        providerOrderLineList.forEach(providerOrderLine -> {
            StockMovementDto stockMovement = new StockMovementDto();
            stockMovement.setArticleDto(articleMapper.fromArticle(providerOrderLine.getArticle()));
            stockMovement.setDateMovement(Instant.now());
            stockMovement.setTypeMoveStock(TypeMoveStock.ENTRANCE);
            stockMovement.setQuantity(providerOrderLine.getQuantity());
            stockMovement.setSourceStockMovement(SourceStockMovement.PROVIDER_ORDER);
            stockMovement.setId_enterprise(providerOrderLine.getId_enterprise());

            stockMovementService.entranceStock(stockMovement);
        });
    }
}
