package com.inventor.management.inventor_management.category.resource.api;

import com.inventor.management.inventor_management.category.dto.CategoryDto;
import com.inventor.management.inventor_management.category.dto.CategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.inventor_management.category.roots.CategoryEndPoint.*;

public interface CategoryApi {
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Save Category",
            description = "This method allow to save category",
            tags = {"CategoryDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Category objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Category objet has invalid")
    })
    ResponseEntity<CategoryDto> saveCategory (@RequestBody @Valid CategoryRequest categoryRequest);

    @PutMapping(
            path = UPDATE_CATEGORY_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update Category",
            description = "This method allow to update category",
            tags = {"CategoryDto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Category objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Catgeory objet has invalid")
    })
    ResponseEntity<CategoryDto> updateCategory(
            @RequestBody @Valid CategoryRequest categoryRequest, @PathVariable(name = "idCategory") Long id
    );

    @GetMapping(
            path = FIND_CATEGORY_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out a category by ID",
            description = "This method allow to find out a category with ID",
            tags = {"CategoryDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Category was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing category exist in DB with ID provided")
    })
    ResponseEntity<CategoryDto> getCategory (@PathVariable(name = "idCategory") Long id);

    @GetMapping(
            path = FIND_CATEGORY_BY_CODE_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out a category by code_category",
            description = "This method allow to find out a category with code_category",tags = {"CategoryDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Category was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing category exist in DB with code_category provided")
    })
    ResponseEntity<CategoryDto> getCodeCategory (@PathVariable(name = "idCodeCategory") String codeCategory);

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return list of categories",
            description = "This method allow to research and return all categories that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All categories were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<CategoryDto>> listCategory();

    @DeleteMapping(
            path = DELETE_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Delete a category",
            description = "This method allow to delete a category by ID",
            tags = {"CategoryDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Category has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<?> deleteCategory (@PathVariable(name = "idCategory") Long id);
}
