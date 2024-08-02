package com.inventor.management.category.resource.restcontroller;

import com.inventor.management.category.dto.CategoryDto;
import com.inventor.management.category.service.CategoryService;
import com.inventor.management.category.resource.api.CategoryApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.category.roots.CategoryEndPoint.CATEGORY_ENDPOINT;

@RestController
@AllArgsConstructor
@RequestMapping(CATEGORY_ENDPOINT)
public class CategoryRestController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
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
