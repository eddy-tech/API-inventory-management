package com.inventor.management.web.api;

import com.inventor.management.dto.StockMovementDto;
import com.inventor.management.entities.StockMovement;
import com.inventor.management.roots.StockMovementEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Api(StockMovementEndPoint.STOCK_MOVEMENT_ENDPOINT)
public interface    StockMovementApi {

    @GetMapping (value = StockMovementEndPoint.ARTICLE_REAL_STOCK)
    @ApiOperation(value = "Get Real stock article", notes = "This method allow to calculate an article real stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been found"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    BigDecimal stockRealArticle (@PathVariable(name = "idArticle") Long articleId);

    @GetMapping (value = StockMovementEndPoint.LIST_STOCK_MOVEMENT_ARTICLE)
    @ApiOperation(value = "Get list of stock movement article", notes = "This method allow to show all article stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been found"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    List<StockMovementDto> listStockMovementArticle (@PathVariable(name = "idArticle") Long articleId);

    @PostMapping (value = StockMovementEndPoint.ENTRANCE_STOCK)
    @ApiOperation(value = "Get Real stock article", notes = "This method allow to calculate an article real stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    StockMovementDto entranceStock (@RequestBody StockMovementDto stockMovement);

    @PostMapping (value = StockMovementEndPoint.EXIT_STOCK)
    @ApiOperation(value = "Get Real stock article", notes = "This method allow to calculate an article real stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    StockMovementDto exitStock (@RequestBody StockMovementDto stockMovement);

    @PostMapping (value = StockMovementEndPoint.STOCK_CORRECTION_POSITIVE)
    @ApiOperation(value = "Get Real stock article", notes = "This method allow to calculate an article real stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    StockMovementDto correctionStockPositive (@RequestBody StockMovementDto stockMovement);

    @PostMapping (value = StockMovementEndPoint.STOCK_CORRECTION_NEGATIVE)
    @ApiOperation(value = "Get Real stock article", notes = "This method allow to calculate an article real stock",
            response = StockMovement.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Stock article objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "stock article objet has invalid")
    })
    StockMovementDto correctionStockNegative (@RequestBody StockMovementDto stockMovement);
}
