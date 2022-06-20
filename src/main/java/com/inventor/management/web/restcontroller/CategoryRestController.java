package com.inventor.management.web.restcontroller;

import com.inventor.management.dto.CategoryDto;
import com.inventor.management.services.interfaces.CategoryService;
import com.inventor.management.web.api.CategoryApi;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryRestController implements CategoryApi {

    private CategoryService categoryService;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
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
