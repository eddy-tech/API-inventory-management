package com.inventor.management.inventor_management.stockMovement.resource.api;

import com.inventor.management.inventor_management.stockMovement.dto.StockMovementDto;
import com.inventor.management.inventor_management.stockMovement.dto.StockMovementRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.inventor.management.inventor_management.stockMovement.roots.StockMovementEndPoint.*;

public interface StockMovementApi {
    @GetMapping (ARTICLE_REAL_STOCK)
    @Operation(
            summary = "Get Real stock article",
            description = "This method allow to calculate an article real stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Stock article objet has been found"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<BigDecimal> stockRealArticle (@PathVariable(name = "idArticle") Long articleId);
    @GetMapping (LIST_STOCK_MOVEMENT_ARTICLE)
    @Operation(
            summary = "Get list of stock movement article",
            description = "This method allow to show all article stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stock article objet has been found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = StockMovementDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<List<StockMovementDto>> listStockMovementArticle (@PathVariable(name = "idArticle") Long articleId);
    @PostMapping (ENTRANCE_STOCK)
    @Operation(
            summary = "Get Real stock article",
            description = "This method allow to calculate an article real stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Stock article objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<StockMovementDto> entranceStock (@RequestBody StockMovementRequest stockMovement);
    @PostMapping(EXIT_STOCK)
    @Operation(
            summary = "Get Real stock article", description = "This method allow to calculate an article real stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Stock article objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<StockMovementDto> exitStock (@RequestBody StockMovementRequest stockMovement);
    @PostMapping(STOCK_CORRECTION_POSITIVE)
    @Operation(
            summary = "Get Real stock article",
            description = "This method allow to calculate an article real stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Stock article objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<StockMovementDto> correctionStockPositive (@RequestBody StockMovementRequest stockMovement);
    @PostMapping(STOCK_CORRECTION_NEGATIVE)
    @Operation(
            summary = "Get Real stock article",
            description = "This method allow to calculate an article real stock",
            tags = {"StockMovement"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Stock article objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "stock article objet has invalid")
    })
    ResponseEntity<StockMovementDto> correctionStockNegative (@RequestBody StockMovementRequest stockMovement);
}
