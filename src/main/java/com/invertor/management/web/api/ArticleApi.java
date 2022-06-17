package com.invertor.management.web.api;

import com.invertor.management.dto.ArticleDto;
import com.invertor.management.utils.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(Constants.API_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = Constants.API_ROOT + "/articles",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save article", notes = "This method ")
    ArticleDto saveArticle (@RequestBody ArticleDto articleDto);

    @PutMapping(value = Constants.API_ROOT +"/articles",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto updateArticle (@RequestBody ArticleDto articleDto);

    @GetMapping(value = Constants.API_ROOT +"/articles/{idArticle}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto getArticle (@PathVariable(name = "idArticle") Long id);

    @GetMapping(value = Constants.API_ROOT +"/articles/{idCodeArticle}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ArticleDto getCodeArticle (@PathVariable(name = "idCodeArticle") String codeArticle);

    @GetMapping(value = Constants.API_ROOT +"/articles",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> listArticle ();

    @DeleteMapping(value = Constants.API_ROOT +"/articles/{idArticle}")
    void deleteArticle (@PathVariable(name = "idArticle") Long id);
}
