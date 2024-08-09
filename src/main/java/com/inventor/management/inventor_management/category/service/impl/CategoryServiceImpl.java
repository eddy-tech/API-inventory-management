package com.inventor.management.inventor_management.category.service.impl;

import com.inventor.management.core.validator.ObjectValidator;
import com.inventor.management.inventor_management.article.repository.ArticleRepository;
import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.entity.Category;
import com.inventor.management.inventor_management.category.mapper.CategoryMapper;
import com.inventor.management.inventor_management.category.repository.CategoryRepository;
import com.inventor.management.inventor_management.category.service.CategoryService;
import com.inventor.management.core.exceptions.EntityNotFoundException;
import com.inventor.management.core.exceptions.InvalidOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.inventor.management.inventor_management.core.utils.Constants.DELETE_CATEGORY;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;
    private final CategoryMapper categoryMapper;
    private final ObjectValidator validator;

    @Override
    public CategoryDto saveCategory(CategoryRequest categoryRequest) {
        validator.validate(categoryRequest);
        return categoryMapper.fromCategoryDto(
                categoryRepository.save(
                        categoryMapper.fromCategory(categoryRequest)
                )
        );
    }

    @Override
    public CategoryDto updateCategory(CategoryRequest categoryRequest, Long id) {
        validator.validate(categoryRequest);
        var category = this.findById(id);
        category.setDesignation(categoryRequest.designation());

        return categoryMapper.fromCategoryDto(categoryRepository.save(category));
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(
                        "Nothing Category with ID ="+id+"has been found in DataBase"
                        )
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
                    "Nothing code category with ID="+codeCategory+"has been found in database"
            );

        var category = categoryRepository.findByCodeCategory(codeCategory);
        return categoryMapper.fromCategoryDto(category);
    }

    @Override
    public List<CategoryDto> listCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::fromCategoryDto)
                .toList();
    }

    @Override
    public void deleteCategory(Long id) {
        if(id == null){
            log.error("Category ID is invalid");
            return;
        }

        var articleList = articleRepository.findAllByCategoryId(id);
        if(!articleList.isEmpty()){
            throw new InvalidOperationException(DELETE_CATEGORY);
        }

        categoryRepository.deleteById(id);
    }
}
