package com.inventor.management.validators;

import com.inventor.management.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate (ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if(articleDto == null){
            errors.add("Can you enter your article code");
            errors.add("Can you enter your designation article");
            errors.add("Can you enter your unit price out free of article");
            errors.add("Can you enter your rate TVA article");
            errors.add("Can you enter your unit price all taxes includes");
            errors.add("Can you select at least one category");

            return errors;
        }

        if(!StringUtils.hasLength(articleDto.getCodeArticle())){
            errors.add("Can you enter your article code");
        }
        if(!StringUtils.hasLength(articleDto.getDesignation())){
            errors.add("Can you enter your designation article");
        }
        if(articleDto.getUnitPriceHt() == null){
            errors.add("Can you enter your unit price out free of article");
        }
        if(articleDto.getRateTax() == null){
            errors.add("Can you enter your rate TVA article");
        }
        if(articleDto.getUnitPriceTtc() == null){
            errors.add("Can you enter your unit price all taxes includes");
        }
        if(articleDto.getCategoryDto() == null){
            errors.add("Can you select at least one category");
        }

        return errors;
    }
}
