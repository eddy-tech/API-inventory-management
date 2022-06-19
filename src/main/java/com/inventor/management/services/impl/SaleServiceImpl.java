package com.inventor.management.services.impl;

import com.inventor.management.dto.SaleDto;
import com.inventor.management.entities.Article;
import com.inventor.management.entities.Sale;
import com.inventor.management.entities.SaleLine;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.mapper.StockMapperImpl;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.SaleLineRepository;
import com.inventor.management.repository.SaleRepository;
import com.inventor.management.services.interfaces.SaleService;
import com.inventor.management.validators.SaleValidator;
import com.inventor.management.exceptions.ErrorCodes;
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
public class SaleServiceImpl implements SaleService {

    private ArticleRepository articleRepository;
    private SaleRepository saleRepository;
    private SaleLineRepository saleLineRepository;
    private StockMapperImpl dtoMapper;


    @Override
    public SaleDto saveSale(SaleDto saleDto) {
        List<String> errors = SaleValidator.validate(saleDto);
        if(!errors.isEmpty()){
            log.error("Sale is invalid");
            throw new InvalidEntityException("Sale objet is invalid", ErrorCodes.SALE_NOT_VALID,errors);
        }

        List<String> articleError = new ArrayList<>();

        saleDto.getSaleLines().forEach(saleLine -> {
            Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
            if(article.isEmpty()){
                articleError.add("Nothing article with ID ="+saleLine.getArticle().getId()+"was found in database");
            }
        });

        if(!articleError.isEmpty()){
            log.error("One or more articles were not found in the database,",errors);
            throw new InvalidEntityException("One or more articles were not found in database",ErrorCodes.SALE_NOT_VALID,errors);
        }

        Sale sale = dtoMapper.fromSaleDto(saleDto);
        Sale savedSale = saleRepository.save(sale);

        saleDto.getSaleLines().forEach(saleLine -> {
            SaleLine saleLines = dtoMapper.fromSaleLineDto(saleLine);
            saleLines.setSale(savedSale);
            saleLineRepository.save(saleLines);
        });

        return dtoMapper.fromSale(savedSale);
    }

    @Override
    public SaleDto updateSale(SaleDto saleDto) {
        List<String> errors = SaleValidator.validate(saleDto);
        if(!errors.isEmpty()){
            log.error("Sale is invalid",saleDto);
            throw new InvalidEntityException("Sale is invalid",ErrorCodes.SALE_NOT_VALID,errors);
        }

        List<String> articleErrors = new ArrayList<>();

        if(saleDto.getSaleLines()!= null){
            saleDto.getSaleLines().forEach(saleLine -> {
                if(saleLine.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(saleLine.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("Nothing article with ID ="+saleLine.getArticle().getId()+"was found in database");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.error("One or more articles were not found in database",errors);
            throw new InvalidEntityException("One or more articles were not found in databse",ErrorCodes.SALE_NOT_VALID,articleErrors);
        }

        Sale sale = dtoMapper.fromSaleDto(saleDto);
        Sale updatedSale = saleRepository.save(sale);

        if(saleDto.getSaleLines() != null){
            saleDto.getSaleLines().forEach(saleLine -> {
                SaleLine saveSaleLine = dtoMapper.fromSaleLineDto(saleLine);
                saveSaleLine.setSale(updatedSale);
                saleLineRepository.save(saveSaleLine);
            });
        }
        return dtoMapper.fromSale(updatedSale);
    }

    @Override
    public SaleDto getSale(Long id) {
        if(id == null){
            log.error("Id Sale is NULL");
            return null;
        }
        Sale sale = saleRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Sale was found with ID ="+id+"in database",ErrorCodes.SALE_NOT_FOUND));

        return dtoMapper.fromSale(sale);
    }

    @Override
    public SaleDto getCodeSale(String codeSale) {
        if(!StringUtils.hasLength(codeSale)){
            log.error("code Sale is invalid");
            throw new EntityNotFoundException("Nothing code sale with ID ="+codeSale+"was found in database",ErrorCodes.SALE_NOT_FOUND);
        }

        Sale sale = saleRepository.findSaleByCodeSale(codeSale);
        return dtoMapper.fromSale(sale);
    }

    @Override
    public List<SaleDto> listSale() {
        List<Sale> saleList = saleRepository.findAll();
        List<SaleDto> saleDtoList = saleList.stream()
                .map(sale -> dtoMapper.fromSale(sale)).collect(Collectors.toList());

        return saleDtoList;
    }

    @Override
    public void deleteSale(Long id) {
        if(id == null){
            log.error("Sale ID is NULL");
            return;
        }

        saleRepository.deleteById(id);
    }
}
