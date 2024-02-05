package com.inventor.management.services.impl;

import com.inventor.management.entities.Article;
import com.inventor.management.entities.Category;
import com.inventor.management.exceptions.EntityNotFoundException;
import com.inventor.management.exceptions.InvalidEntityException;
import com.inventor.management.dto.CategoryDto;
import com.inventor.management.exceptions.ErrorCodes;
import com.inventor.management.exceptions.InvalidOperationException;
import com.inventor.management.mapper.CategoryMapper;
import com.inventor.management.repository.ArticleRepository;
import com.inventor.management.repository.CategoryRepository;
import com.inventor.management.services.interfaces.CategoryService;
import com.inventor.management.validators.CategoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Category is invalid" + categoryDto);
            throw new InvalidEntityException(
                    "Category is invalid", ErrorCodes.CATEGORY_NOT_VALID, errors
            );
        }

        var category = categoryMapper.fromCategoryDto(categoryDto);
        return categoryMapper.fromCategory(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        List<String> errors = CategoryValidator.validate(categoryDto);
        if(!errors.isEmpty()){
            log.error("Category is invalid" + categoryDto);
            throw new InvalidEntityException(
                    "Category is invalid", ErrorCodes.CATEGORY_NOT_VALID,errors
            );
        }

        Category updateCategory = categoryRepository.save(categoryMapper.fromCategoryDto(categoryDto));
        return categoryMapper.fromCategory(updateCategory);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if(id == null){
            log.error("Category ID is invalid");
            return null;
        }
        var category = categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Category with ID ="+id+"has been found in DataBase",
                        ErrorCodes.ARTICLES_NOT_FOUND)
                );

        return categoryMapper.fromCategory(category);
    }

    @Override
    public CategoryDto getCodeCategory(String codeCategory) {
        if(!StringUtils.hasLength(codeCategory))
            throw new EntityNotFoundException(
                    "Nothing code category with ID="+codeCategory+"has been found in database",
                    ErrorCodes.CATEGORY_NOT_FOUND
            );

        var category = categoryRepository.findByCodeCategory(codeCategory);
        return categoryMapper.fromCategory(category);
    }

    @Override
    public List<CategoryDto> listCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(categoryMapper::fromCategory).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long id) {
        if(id == null){
            log.error("Category ID is invalid");
            return;
        }

        List<Article> articleList = articleRepository.findAllByCategoryId(id);
        if(!articleList.isEmpty()){
            throw new InvalidOperationException("Unable to delete a category that already using by article",
                    ErrorCodes.ARTICLE_ALREADY_IN_USE);
        }

        categoryRepository.deleteById(id);
    }
}
