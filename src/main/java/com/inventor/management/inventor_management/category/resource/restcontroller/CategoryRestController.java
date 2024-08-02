package com.inventor.management.inventor_management.category.resource.restcontroller;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.service.CategoryService;
import com.inventor.management.inventor_management.category.resource.api.CategoryApi;
import com.inventor.management.inventor_management.category.roots.CategoryEndPoint;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(CategoryEndPoint.CATEGORY_ENDPOINT)
public class CategoryRestController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public CategoryDto saveCategory(CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    @Override
    public CategoryDto updateCategory(CategoryRequest categoryRequest, Long id) {
        return categoryService.updateCategory(categoryRequest, id);
    }

    @Override
    public CategoryDto getCategory(Long id) {
        return categoryService.getCategory(id);
    }

    @Override
    public CategoryDto getCodeCategory(String codeCategory) {
        return categoryService.getCodeCategory(codeCategory);
    }

    @Override
    public List<CategoryDto> listCategory() {
        return categoryService.listCategory();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }
}
