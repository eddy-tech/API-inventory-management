package com.inventor.management.inventor_management.article.resource.api;

import com.inventor.management.inventor_management.article.dto.ArticleDto;
import com.inventor.management.inventor_management.article.dto.ArticleRequest;
import com.inventor.management.inventor_management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.saleLine.dto.SaleLineDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.inventor_management.article.roots.ArticleEndPoint.*;

public interface ArticleApi {
    @PostMapping(
            value = ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Save article",
            description = "This method allow to save an article",
            tags = {"Article"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Article objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Article objet has invalid")
    })
    ArticleDto saveArticle (@RequestBody ArticleRequest articleRequest);

    @PutMapping(
            value = UPDATE_ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update article",
            description = "This method allow to update an article",
            tags = {"Article"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Article objet has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Article objet has invalid")
    })
    ArticleDto updateArticle (@RequestBody ArticleRequest articleRequest, @PathVariable(name = "idArticle") Long id);

    @GetMapping(
            value = FIND_ARTICLE_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out an article by ID",
            description = "This method allow to find out an article with ID",
            tags = {"Article"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Article has been found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing article exist in DB with ID provided")
    })
    ArticleDto getArticle (@PathVariable(name = "idArticle") Long id);

    @GetMapping(
            value = FIND_ARTICLE_BY_CODE_ARTICLE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out an article by code_article",
            description = "This method allow to find out an article with code_article",
            tags = {"Article"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Article has been found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing article exist in DB with code_article provided")
    })
    ArticleDto getCodeArticle (@PathVariable(name = "idCodeArticle") String codeArticle);

    @GetMapping(
            value = ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return list of articles",
            description = "This method allow to research and return all articles that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All articles had been found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<ArticleDto> listArticle ();
    @GetMapping(
            value = FIND_HISTORY_SALES,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Return history of sales",
            description = "This method allow to research and return all sales histories of an article that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All sales histories had been found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<SaleLineDto> findHistorySales (@PathVariable(name = "idArticle") Long articleId);

    @GetMapping(
            value = FIND_HISTORY_CUSTOMER_ORDER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return history of customer order",
            description = "This method allow to research and return all customer order histories of an article that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All customer order histories were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<CustomerOrderLineDto> findHistoryCustomerOrder (@PathVariable(name = "idArticle")Long articleId);

    @GetMapping(
            value = FIND_HISTORY_PROVIDER_ORDER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return history of provider order",
            description = "This method allow to research and return all provider order histories of an article that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All provider order histories were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<ProviderOrderLineDto> findHistoryProviderOrder (@PathVariable(name = "idArticle")Long articleId);

    @GetMapping(
            value = FIND_ALL_ARTICLE_BY_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return history of article by category",
            description = "This method allow to research and return all article histories by category that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All articles histories by category were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"
            )
    })
    List<ArticleDto> findAllArticleByCategory (@PathVariable(name = "idCategory")Long categoryId);

    @DeleteMapping(value = DELETE_ARTICLE)
    @Operation(
            summary = "Delete an article",
            description = "This method allow to delete an article by ID",
            tags = {"Article"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Article has been deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    void deleteArticle (@PathVariable(name = "idArticle") Long id);
}
