package com.inventor.management.inventor_management.category.resource.restcontroller;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import com.inventor.management.inventor_management.category.service.CategoryService;
import com.inventor.management.inventor_management.category.resource.api.CategoryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.inventor.management.inventor_management.category.roots.CategoryEndPoint.CATEGORY_ENDPOINT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(CATEGORY_ENDPOINT)
public class CategoryRestController implements CategoryApi {
    private final CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryDto> saveCategory(CategoryRequest categoryRequest) {
        return ResponseEntity.status(CREATED)
                .body(categoryService.saveCategory(categoryRequest));
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(CategoryRequest categoryRequest, Long id) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequest, id));
    }

    @Override
    public ResponseEntity<CategoryDto> getCategory(Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @Override
    public ResponseEntity<CategoryDto> getCodeCategory(String codeCategory) {
        return ResponseEntity.ok(categoryService.getCodeCategory(codeCategory));
    }

    @Override
    public ResponseEntity<List<CategoryDto>> listCategory() {
        return ResponseEntity.ok(categoryService.listCategory());
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
