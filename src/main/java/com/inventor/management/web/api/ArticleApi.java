package com.inventor.management.web.api;

import com.inventor.management.dto.ArticleDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.dto.ProviderOrderLineDto;
import com.inventor.management.dto.SaleLineDto;
import com.inventor.management.roots.ArticleEndPoint;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = ArticleEndPoint.ARTICLE_ENDPOINT)
public interface ArticleApi {

    @PostMapping(value = ArticleEndPoint.ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save article", notes = "This method allow to save an article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 400, message = "Article objet has invalid")
    })
    ArticleDto saveArticle (@RequestBody ArticleDto articleDto);

    @PutMapping(value = ArticleEndPoint.ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update article", notes = "This method allow to update an article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article objet has been updated"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 400, message = "Article objet has invalid")
    })
    ArticleDto updateArticle (@RequestBody ArticleDto articleDto);

    @GetMapping(value = ArticleEndPoint.FIND_ARTICLE_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out an article by ID",
            notes = "This method allow to find out an article with ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Article was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing article exist in DB with ID provided")
    })
    ArticleDto getArticle (@PathVariable(name = "idArticle") Long id);

    @GetMapping(value = ArticleEndPoint.FIND_ARTICLE_BY_CODE_ARTICLE,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out an article by code_article",
            notes = "This method allow to find out an article with code_article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing article exist in DB with code_article provided")
    })
    ArticleDto getCodeArticle (@PathVariable(name = "idCodeArticle") String codeArticle);

    @GetMapping(value = ArticleEndPoint.ARTICLE_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return list of articles",
            notes = "This method allow to research and return all articles that exist in DB",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All articles were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<ArticleDto> listArticle ();

    @GetMapping(value = ArticleEndPoint.FIND_HISTORY_SALES,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return history of sales",
            notes = "This method allow to research and return all sales histories of an article that exist in DB",responseContainer = "List<SaleLineDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All sales histories were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<SaleLineDto> findHistorySales (@PathVariable(name = "idArticle") Long articleId);

    @GetMapping(value = ArticleEndPoint.FIND_HISTORY_CUSTOMER_ORDER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return history of customer order",
            notes = "This method allow to research and return all customer order histories of an article that exist in DB",responseContainer = "List<CustomerOrderLine>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All customer order histories were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<CustomerOrderLineDto> findHistoryCustomerOrder (@PathVariable(name = "idArticle")Long articleId);

    @GetMapping(value = ArticleEndPoint.FIND_HISTORY_PROVIDER_ORDER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return history of provider order",
            notes = "This method allow to research and return all provider order histories of an article that exist in DB",responseContainer = "List<ProviderOrderLineDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All provider order histories were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<ProviderOrderLineDto> findHistoryProviderOrder (@PathVariable(name = "idArticle")Long articleId);

    @GetMapping(value = ArticleEndPoint.FIND_ALL_ARTICLE_BY_CATEGORY,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return history of article by category",
            notes = "This method allow to research and return all article histories by category that exist in DB",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All articles histories by category were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<ArticleDto> findAllArticleByCategory (@PathVariable(name = "idCategory")Long categoryId);

    @DeleteMapping(value = ArticleEndPoint.DELETE_ARTICLE)
    @ApiOperation(value = "Delete an article",
            notes = "This method allow to delete an article by ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Article has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteArticle (@PathVariable(name = "idArticle") Long id);
}
