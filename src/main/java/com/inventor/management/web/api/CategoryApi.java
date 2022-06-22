package com.inventor.management.web.api;

import com.inventor.management.dto.CategoryDto;
import com.inventor.management.roots.CategoryEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = CategoryEndPoint.CATEGORY_ENDPOINT)
public interface CategoryApi {

    @PostMapping(value = CategoryEndPoint.CATEGORY_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Category", notes = "This method allow to save category", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Category objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Category objet has invalid")
    })
    CategoryDto saveCategory (@RequestBody CategoryDto categoryDto);

    @PutMapping(value = CategoryEndPoint.CATEGORY_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Category",notes = "This method allow to update category",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Category objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Catgeory objet has invalid")
    })
    CategoryDto updateCategory (@RequestBody CategoryDto categoryDto);

    @GetMapping(value = CategoryEndPoint.FIND_CATEGORY_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out a category by ID",
            notes = "This method allow to find out a category with ID",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Category was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing category exist in DB with ID provided")
    })
    CategoryDto getCategory (@PathVariable(name = "idCategory") Long id);

    @GetMapping(value = CategoryEndPoint.FIND_CATEGORY_BY_CODE_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out a category by code_category",
            notes = "This method allow to find out a category with code_category",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing category exist in DB with code_category provided")
    })
    CategoryDto getCodeCategory (@PathVariable(name = "idCodeCategory") String codeCategory);

    @GetMapping(value = CategoryEndPoint.CATEGORY_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return list of categories",
            notes = "This method allow to research and return all categories that exist in DB",responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All categories were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<CategoryDto> listCategory ();

    @DeleteMapping(value = CategoryEndPoint.DELETE_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a category",
            notes = "This method allow to delete a category by ID",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Category has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteCategory (@PathVariable(name = "idCategory") Long id);
}
