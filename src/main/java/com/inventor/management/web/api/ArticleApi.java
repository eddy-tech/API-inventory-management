package com.inventor.management.web.api;

import com.inventor.management.utils.Constants;
import com.inventor.management.dto.ArticleDto;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = Constants.API_ROOT + "/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save article", notes = "This method allow to save an article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article objet has been saved"),
            @ApiResponse(code = 400, message = "Article objet has invalid")
    })
    ArticleDto saveArticle (@RequestBody ArticleDto articleDto);

    @PutMapping(value = Constants.API_ROOT +"/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update article", notes = "This method allow to update an article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article objet has been updated"),
            @ApiResponse(code = 400, message = "Article objet has invalid")
    })
    ArticleDto updateArticle (@RequestBody ArticleDto articleDto);

    @GetMapping(value = Constants.API_ROOT +"/articles/{idArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out an article by ID",
            notes = "This method allow to find out an article with ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Article was found in DB"),
            @ApiResponse(code = 404, message = "Nothing article exist in DB with ID provided")
    })
    ArticleDto getArticle (@PathVariable(name = "idArticle") Long id);

    @GetMapping(value = Constants.API_ROOT +"/articles/{idCodeArticle}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out an article by code_article",
            notes = "This method allow to find out an article with code_article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Article was found in DB"),
            @ApiResponse(code = 404, message = "Nothing article exist in DB with code_article provided")
    })
    ArticleDto getCodeArticle (@PathVariable(name = "idCodeArticle") String codeArticle);

    @GetMapping(value = Constants.API_ROOT +"/articles",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return list of articles",
            notes = "This method allow to research and return all articles that exist in DB",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All articles were found in DB / Empty list")
    })
    List<ArticleDto> listArticle ();

    @DeleteMapping(value = Constants.API_ROOT +"/articles/{idArticle}")
    @ApiOperation(value = "Delete an article",
            notes = "This method allow to delete an article by ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Article has been deleted")
    })
    void deleteArticle (@PathVariable(name = "idArticle") Long id);
}
