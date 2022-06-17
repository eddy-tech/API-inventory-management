package com.invertor.management.services.impl;

import com.invertor.management.dto.CategoryDto;
import com.invertor.management.entities.Category;
import com.invertor.management.exceptions.EntityNotFoundException;
import com.invertor.management.exceptions.ErrorCodes;
import com.invertor.management.exceptions.InvalidEntityException;
import com.invertor.management.mapper.StockMapperImpl;
import com.invertor.management.repository.CategoryRepository;
import com.invertor.management.services.interfaces.CategoryService;
import com.invertor.management.validators.CategoryValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private StockMapperImpl dtoMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Category is invalid",categoryDto);
            throw new InvalidEntityException("Category is invalid", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }

        Category category = dtoMapper.fromCategoryDto(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return dtoMapper.fromCategory(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Category is invalid",categoryDto);
            throw new InvalidEntityException("Category is invalid", ErrorCodes.CATEGORY_NOT_VALID,errors);
        }

        Category updateCategory = categoryRepository.save(dtoMapper.fromCategoryDto(categoryDto));
        return dtoMapper.fromCategory(updateCategory);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if(id == null){
            log.error("Category ID is invalid");
            return null;
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Nothing Category with ID ="+id+"was found in DataBase",ErrorCodes.ARTICLES_NOT_FOUND));

        return dtoMapper.fromCategory(category);
    }

    @Override
    public CategoryDto getCodeCategory(String codeCategory) {
        if(!StringUtils.hasLength(codeCategory))
            throw new EntityNotFoundException("Nothing code category with ID="+codeCategory+"was found in database",ErrorCodes.CATEGORY_NOT_FOUND);
        Category category = categoryRepository.findCategoryByCodeCategory(codeCategory);

        return dtoMapper.fromCategory(category);
    }

    @Override
    public List<CategoryDto> listCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream()
                .map(category -> dtoMapper.fromCategory(category)).collect(Collectors.toList());

        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Long id) {
        if(id == null){
            log.error("Catgeory ID is invalid");
            return;
        }

        categoryRepository.deleteById(id);

    }
}
