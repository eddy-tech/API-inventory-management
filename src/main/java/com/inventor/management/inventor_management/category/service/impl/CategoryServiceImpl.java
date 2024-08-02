package com.inventor.management.inventor_management.category.service.impl;

import com.inventor.management.inventor_management.article.entity.Article;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.category.mapper.CategoryMapper;
import com.inventor.management.inventor_management.category.repository.CategoryRepository;
import com.inventor.management.inventor_management.category.service.CategoryService;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.ErrorCodes;
import com.inventor.management.core.exceptions.InvalidOperationException;
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
    public CategoryDto saveCategory(CategoryRequest categoryRequest) {
        return categoryMapper.fromCategoryDto(
                categoryRepository.save(
                        categoryMapper.fromCategory(categoryRequest)
                )
        );
    }

    @Override
    public CategoryDto updateCategory(CategoryRequest categoryRequest, Long id) {
        var category = this.findById(id);
        category.setDesignation(categoryRequest.designation());

        return categoryMapper.fromCategoryDto(categoryRepository.save(category));
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Category with ID ="+id+"has been found in DataBase",
                        ErrorCodes.ARTICLES_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto getCategory(Long id) {
        if(id == null){
            log.error("Category ID is invalid");
            return null;
        }

        return categoryMapper.fromCategoryDto(this.findById(id));
    }

    @Override
    public CategoryDto getCodeCategory(String codeCategory) {
        if(!StringUtils.hasLength(codeCategory))
            throw new EntityNotFoundException(
                    "Nothing code category with ID="+codeCategory+"has been found in database",
                    ErrorCodes.CATEGORY_NOT_FOUND
            );

        var category = categoryRepository.findByCodeCategory(codeCategory);
        return categoryMapper.fromCategoryDto(category);
    }

    @Override
    public List<CategoryDto> listCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::fromCategoryDto)
                .collect(Collectors.toList());
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
