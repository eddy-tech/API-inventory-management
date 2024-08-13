package com.inventor.management.inventor_management.providerOrder.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderRequest;
import com.inventor.management.inventor_management.providerOrder.repository.ProviderOrderRepository;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.provider.mapper.ProviderMapper;
import com.inventor.management.inventor_management.provider.entity.Provider;
import com.inventor.management.inventor_management.providerOrder.entity.ProviderOrder;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.providerOrderLine.entity.ProviderOrderLine;
import com.inventor.management.inventor_management.providerOrderLine.repository.ProviderOrderLineRepository;
import com.inventor.management.inventor_management.provider.repository.ProviderRepository;
import com.inventor.management.inventor_management.providerOrder.service.ProviderOrderService;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.inventor.management.inventor_management.core.enums.SourceStockMovement.PROVIDER_ORDER;
import static com.inventor.management.inventor_management.core.enums.TypeMoveStock.ENTRANCE;
import static com.inventor.management.inventor_management.core.utils.RandomGenerator.generateRandomCode;

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
    private final ObjectValidator validator;

    private void checkIdOrder (Long orderId){
        if(orderId == null) {
            log.error("provider order ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null ID");
        }
    }

    private void checkIdOrderLine (Long orderLineId) {
        if(orderLineId == null) {
            log.error("provider order Line ID is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null order line");
        }
    }

    private void checkIdArticle (Long idArticle){
        if(idArticle == null){
            log.error("ID of"+"new"+"is NULL");
            throw new InvalidOperationException("Unable to edit state order with a" + "new" + "article ID null");
        }
    }

    private ProviderOrderDto checkStateOrder(Long orderId){
        var providerOrder = getProviderOrder(orderId);
        if(providerOrder.isOrderDelivered())
            throw new InvalidOperationException("Unable to edit state order with null ID");

        return providerOrder;
    }

    private ProviderOrderLine findProviderOrderLine (Long orderLineId){
        return providerOrderLineRepository.findById(orderLineId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing customer order line has been found with ID ="+ orderLineId)
                );
    }

    private Provider findProvider (Long providerId){
        return providerRepository.findById(providerId)
                .orElseThrow(()->new EntityNotFoundException("Nothing customer was found with ID ="+providerId));
    }

    private Article findArticle (Long articleId){
        return  articleRepository.findById(articleId)
                .orElseThrow(()->new EntityNotFoundException("Nothing article was found with ID ="+articleId));
    }

    private ProviderOrder findProviderOrder (Long providerOrderId){
        return providerOrderRepository.findById(providerOrderId)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Provider order with ID="+ providerOrderId +"was found in database")
                );
    }

    @Override
    public ProviderOrderDto saveProviderOrder(ProviderOrderRequest providerOrderRequest) {
        this.validator.validate(providerOrderRequest);

        var provider = providerRepository.findById(providerOrderRequest.providerId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="
                         + providerOrderRequest.providerId() +
                        "was found in database")
                );

        List<String> articleErrors = new ArrayList<>();

        if(providerOrderRequest.providerOrderLineDto() != null){
            providerOrderRequest.providerOrderLineDto().forEach(providerOrderLineDto -> {
                if(providerOrderLineDto.getArticleDto() != null){
                    var articleDto = articleRepository.findById(providerOrderLineDto.getArticleDto().getId());
                    if(articleDto.isEmpty()){
                        articleErrors.add(
                                "Article with ID ="+providerOrderLineDto.getArticleDto().getId()+"not exist in database"
                        );
                    } else {
                        articleErrors.add("Unable to save provider order with an article null");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article not exist in database");
        }

        var providerOrder = providerMapper.fromProviderOrderRequest(providerOrderRequest, provider);
        providerOrder.setCodeProviderOrder(generateRandomCode(8));
        var savedProviderOrder = providerOrderRepository.save(providerOrder);

        if(providerOrderRequest.providerOrderLineDto() != null){
            providerOrderRequest.providerOrderLineDto().forEach(providerOrderLineDto -> {
                var providerOrderLine = providerMapper.fromProviderOrderLineDto(
                        providerOrderLineDto,
                        findArticle(providerOrderLineDto.getArticleDto().getId()),
                        findProviderOrder(providerOrderLineDto.getProviderOrderDto().getId())
                );
                providerOrderLine.setProviderOrder(savedProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return providerMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateProviderOrder(ProviderOrderRequest providerOrderRequest, Long id) {
        validator.validate(providerOrderRequest);

        var provider = providerRepository.findById(providerOrderRequest.providerId())
                .orElseThrow(()->new EntityNotFoundException("Nothing provider order with ID ="+
                        providerOrderRequest.providerId() + "was found in database"));

        if(id != null && providerOrderRequest.isOrderDelivered())
            throw new InvalidOperationException("Unable to update provider order");

        List<String> articleErrors = new ArrayList<>();

        if(providerOrderRequest.providerId() != null){
            providerOrderRequest.providerOrderLineDto().forEach(providerOrderLineDto -> {
                if(providerOrderLineDto.getArticleDto() != null){
                    var article = articleRepository.findById(providerOrderLineDto.getArticleDto().getId());
                    if(article.isEmpty()){
                        articleErrors.add(
                                "Article with ID ="+ providerOrderLineDto.getArticleDto().getId() +
                                        "was not exit in database"
                        );
                    } else {
                        articleErrors.add("Impossible to save Provider with an article NULL");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new EntityNotFoundException("Article not exist in database");
        }

        var updateProviderOrder = providerOrderRepository.save(
                providerMapper.fromProviderOrderRequest(providerOrderRequest, provider));

        if(providerOrderRequest.providerOrderLineDto() != null){
            providerOrderRequest.providerOrderLineDto().forEach(providerOrderLineDto -> {
                var providerOrderLine = providerMapper.fromProviderOrderLineDto(
                        providerOrderLineDto,
                        findArticle(providerOrderLineDto.getArticleDto().getId()),
                        findProviderOrder(providerOrderLineDto.getProviderOrderDto().getId())
                );
                providerOrderLine.setProviderOrder(updateProviderOrder);
                providerOrderLineRepository.save(providerOrderLine);
            });
        }
        return providerMapper.fromProviderOrder(updateProviderOrder);
    }

    @Override
    public ProviderOrderDto updateStateOrder(Long orderId, StateOrder stateOrder) {
        this.checkIdOrder(orderId);

        if(!StringUtils.hasLength(String.valueOf(stateOrder))) {
            log.error("provider state order is NULL");
            throw new InvalidOperationException("Unable to edit state order with state NULL");
        }

        ProviderOrderDto orderDto = checkStateOrder(orderId);
        orderDto.setStateOrder(stateOrder);
        var providerOrder = providerMapper.toProviderOrder(orderDto);
        var savedProviderOrder = providerOrderRepository.save(providerOrder);
        // MAKE THE STOCK OUT ONLY WHEN PROVIDER ORDER IS DELIVERED
        if(orderDto.isOrderDelivered()){
        // Update the stock state of provider
        updateStockMovementProvider(orderId);
        }

        return providerMapper.fromProviderOrder(savedProviderOrder);
    }

    @Override
    public ProviderOrderDto updateQuantityOrdered(Long orderId, Long orderLineId, BigDecimal quantity) {
        this.checkIdOrder(orderId);
        this.checkIdOrderLine(orderLineId);
        if(quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0) {
            log.error("quantity of customer order is null");
            throw new InvalidOperationException("Unable to edit quantity ordered with null quantity or 0");
        }

        var providerOrder = checkStateOrder(orderId);
        var providerOrderLine = findProviderOrderLine(orderLineId);
        providerOrderLine.setQuantity(quantity);

        providerOrderLineRepository.save(providerOrderLine);

        return providerOrder;
    }

    @Override
    public ProviderOrderDto updateProvider(Long orderId, Long providerId) {
        this.checkIdOrder(orderId);
        if(providerId == null) {
            log.error("customer ID is null");
            throw new InvalidOperationException("Unable to edit state order with null ID");
        }

        var providerOrder = checkStateOrder(orderId);
        var providerOptional = findProvider(providerId);
        var provider = providerMapper.fromProvider(providerOptional);

        providerOrder.setProviderDto(provider);

        return providerMapper.fromProviderOrder(
                providerOrderRepository.save(
                        providerMapper.toProviderOrder(providerOrder)
                )
        );
    }

    @Override
    public ProviderOrderDto updateArticle(Long orderId, Long orderLineId, Long articleId) {
        this.checkIdOrder(orderId);
        this.checkIdOrderLine(orderLineId);
        this.checkIdArticle(articleId);

        var providerOrder = checkStateOrder(orderId);
        var providerOrderLine = findProviderOrderLine(orderLineId);
        var articleOptional = findArticle(articleId);

//        List<String> errors = ArticleValidator.validate(articleMapper.fromArticle(articleOptional));
//        if(!errors.isEmpty()) throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID,errors);

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

        var providerOrder = findProviderOrder(id);
        return providerMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public ProviderOrderDto getCodeProviderOrder(String codeProviderOrder) {
        if(!StringUtils.hasLength(codeProviderOrder)){
            log.error("Provider Order is NULL");
            throw new InvalidEntityException("Nothing provider order with code ="+codeProviderOrder+
                    "was found in database"
            );
        }

        var providerOrder = providerOrderRepository.findByCodeProviderOrder(codeProviderOrder);
        return providerMapper.fromProviderOrder(providerOrder);
    }

    @Override
    public List<ProviderOrderDto> listProviderOrder() {
        List<ProviderOrder> providerOrdersList = providerOrderRepository.findAll();

        return providerOrdersList.stream()
                .map(providerMapper::fromProviderOrder)
                    .toList();
    }

    @Override
    public List<ProviderOrderLineDto> findAllProviderOrdersLinesByProviderOrderId(Long orderId) {
        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(orderId);

        return providerOrderLineList.stream()
                .map(providerMapper::fromProviderOrderLine)
                    .toList();
    }

    @Override
    public void deleteProviderOrder(Long id) {
        if(id == null){
            log.error("Provider order ID is not exist");
            return;
        }

        List<ProviderOrderLine> providerOrderLineList = providerOrderLineRepository.findAllByProviderOrderId(id);
        if(!providerOrderLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete provider order that has already provider order line"
            );
        }

        providerOrderRepository.deleteById(id);
    }

    @Override
    public ProviderOrderDto deleteArticle(Long orderId, Long orderLineId) {
        this.checkIdOrder(orderId);
        this.checkIdOrderLine(orderLineId);
        var providerOrder = checkStateOrder(orderId);
        // JUST TO CHECK CUSTOMER ORDER LINE AND INFORM THE CLIENT IN CASE IT IS ABSENT
        this.findProviderOrderLine(orderLineId);
        providerOrderLineRepository.deleteById(orderLineId);

        return providerOrder;
    }

    public void updateStockMovementProvider (Long orderId){
        providerOrderLineRepository.findAllByProviderOrderId(orderId)
                .forEach(providerOrderLine -> {

            var stockMovement = StockMovementRequest.builder()
                    .articleId(providerOrderLine.getArticle().getId())
                    .dateMovement(Instant.now())
                    .typeMoveStock(ENTRANCE)
                    .quantity(providerOrderLine.getQuantity())
                    .sourceStockMovement(PROVIDER_ORDER)
                            .build();

            stockMovementService.entranceStock(stockMovement);
        });
    }
}
