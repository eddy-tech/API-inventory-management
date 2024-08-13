package com.inventor.management.inventor_management.sale.service.impl;

import com.inventor.management.core.exceptions.BusinessException;
import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.enterprise.entity.Enterprise;
import com.inventor.management.inventor_management.enterprise.repository.EnterpriseRepository;
import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.sale.dto.SaleRequest;
import com.inventor.management.inventor_management.sale.mapper.SaleMapper;
import com.inventor.management.inventor_management.sale.service.SaleService;
import com.inventor.management.inventor_management.saleLine.entity.SaleLine;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidEntityException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import com.inventor.management.inventor_management.saleLine.repository.SaleLineRepository;
import com.inventor.management.inventor_management.sale.repository.SaleRepository;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;
import com.inventor.management.inventor_management.stockMovement.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inventor.management.inventor_management.core.enums.SourceStockMovement.SALE;
import static com.inventor.management.inventor_management.core.enums.TypeMoveStock.EXIT;
import static com.inventor.management.inventor_management.core.utils.RandomGenerator.generateRandomCode;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {
    private final ArticleRepository articleRepository;
    private final SaleRepository saleRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final SaleLineRepository saleLineRepository;
    private final StockMovementService stockMovementService;
    private final SaleMapper saleMapper;
    private final ObjectValidator validator;


    @Override
    public SaleDto saveSale(SaleRequest saleRequest) {
        validator.validate(saleRequest);
        List<String> articleError = new ArrayList<>();
        var enterprise = this.getEnterprise(saleRequest.id_enterprise());

        saleRequest.saleLines().forEach(saleLine -> {
            Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
            if(article.isEmpty()){
                articleError.add("Nothing article with ID ="+ saleLine.getArticle().getId() + "was found in database");
            }
        });

        if(!articleError.isEmpty()){
            log.error("One or more articles were not found in the database");
            throw new InvalidEntityException("One or more articles were not found in database");
        }
        var sale = saleMapper.fromSaleRequest(saleRequest, enterprise);
        sale.setCodeSale(generateRandomCode(8));

        var saveSale = saleRepository.save(sale);

        saleRequest.saleLines().forEach(saleLine -> {
            var article = articleRepository.findById(saleLine.getArticle().getId()).get();
            var saleLines = saleMapper.fromSaleLineDto(saleMapper.fromSaleLine(saleLine), article, saveSale);
            saleLines.setSale(saveSale);
            saleLineRepository.save(saleLines);
            updateStockMovementSale(saleLine);
        });

        return saleMapper.fromSale(saveSale);
    }

    @Override
    public SaleDto updateSale(SaleRequest saleRequest, Long id) {
        validator.validate(saleRequest);
        List<String> articleErrors = new ArrayList<>();
        var enterprise = this.getEnterprise(saleRequest.id_enterprise());

        if(saleRequest.saleLines()!= null){
            saleRequest.saleLines().forEach(saleLine -> {
                if(saleLine.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Nothing article with ID ="+ saleLine.getArticle().getId() +
                                "was found in database"
                        );
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.error("One or more articles were not found in database");
            throw new InvalidEntityException("One or more articles were not found in database", articleErrors);
        }

        var updatedSale = saleRepository.save(saleMapper.fromSaleRequest(saleRequest, enterprise));

        if(saleRequest.saleLines() != null){
            saleRequest.saleLines().forEach(saleLine -> {
                var article = articleRepository.findById(saleLine.getArticle().getId()).get();
                var saveSaleLine = saleMapper.fromSaleLineDto(saleMapper.fromSaleLine(saleLine), article, updatedSale);
                saveSaleLine.setSale(updatedSale);
                saleLineRepository.save(saveSaleLine);
            });
        }
        return saleMapper.fromSale(updatedSale);
    }

    @Override
    public SaleDto getSale(Long id) {
        if(id == null){
            log.error("Id Sale is NULL");
            return null;
        }
        var sale = saleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Sale was found with ID ="+id+"in database"));

        return saleMapper.fromSale(sale);
    }

    @Override
    public SaleDto getCodeSale(String codeSale) {
        if(!StringUtils.hasLength(codeSale)){
            log.error("code Sale is invalid");
            throw new EntityNotFoundException(
                    "Nothing code sale with ID ="+codeSale+"was found in database"
            );
        }

        return saleMapper.fromSale(saleRepository.findByCodeSale(codeSale));
    }

    @Override
    public List<SaleDto> listSale() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::fromSale)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteSale(Long id) {
        if(id == null){
            log.error("Sale ID is NULL");
            return;
        }

        var articleList = articleRepository.findAllByCategoryId(id);
        if(!articleList.isEmpty()){
            throw new InvalidOperationException("Unable to delete sale that has already using article");
        }

        var saleLineList = saleLineRepository.findAllBySaleId(id);
        if(!saleLineList.isEmpty()){
            throw new InvalidOperationException("Unable to delete sale that has already using sale line");
        }

        saleRepository.deleteById(id);
    }

    private void updateStockMovementSale (SaleLine saleLine){
            var stock = StockMovementRequest.builder()
                    .articleId(saleLine.getArticle().getId())
                    .typeMoveStock(EXIT)
                    .sourceStockMovement(SALE)
                    .dateMovement(Instant.now())
                    .quantity(saleLine.getQuantity())
                    .build();

            stockMovementService.exitStock(stock);
    }

    private Enterprise getEnterprise(Long id) {
        return enterpriseRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Enterprise not found"));
    }
}
