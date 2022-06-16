package com.invertor.management.services.impl;

import com.invertor.management.dto.SaleDto;
import com.invertor.management.entities.Article;
import com.invertor.management.entities.Sale;
import com.invertor.management.entities.SaleLine;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.ArticleRepository;
import com.invertor.management.repository.SaleLineRepository;
import com.invertor.management.repository.SaleRepository;
import com.invertor.management.services.interfaces.SaleService;
import com.invertor.management.validators.SaleValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            SaleLine saleLine1 = dtoMapper.fromSaleLineDto(saleLine)
        });
        return dtoMapper.fromSale(savedSale);
    }

    @Override
    public SaleDto updateSale(SaleDto saleDto) {
        return null;
    }

    @Override
    public SaleDto getSale(Long id) {
        return null;
    }

    @Override
    public SaleDto getCodeSale(String codeSale) {
        return null;
    }

    @Override
    public List<SaleDto> listSale() {
        return null;
    }

    @Override
    public void deleteSale(Long id) {

    }
}
