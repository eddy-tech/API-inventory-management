package com.inventor.management.inventor_management.sale.resource.api;

import com.inventor.management.inventor_management.sale.dto.SaleDto;
import com.inventor.management.inventor_management.sale.dto.SaleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.inventor_management.sale.roots.SaleEndPoint.*;

public interface SaleApi {
    @PostMapping
    @Operation(
            summary = "Save Sale",
            description = "This method allow to save sale",
            tags = {"SaleDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Sale objet has been saved"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Sale objet has invalid")
    })
    ResponseEntity<SaleDto> saveSale (@RequestBody @Valid SaleRequest saleRequest);
    @PutMapping(UPDATE_SALE_ENDPOINT)
    @Operation(
            summary = "Update Sale",
            description = "This method allow to update sale",
            tags = {"SaleDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Sale objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Sale objet has invalid")
    })
    ResponseEntity<SaleDto> updateSale (
            @PathVariable(name = "idSale") Long saleId, @RequestBody @Valid SaleRequest saleRequest
    );
    @GetMapping(FIND_SALE_BY_ID)
    @Operation(
            summary = "Find out a sale by ID",
            description = "This method allow to find out a sale with ID",
            tags = {"SaleDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Sale was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing sale exist in DB with ID provided")
    })
    ResponseEntity<SaleDto> getSale (@PathVariable(name = "idSale") Long id);
    @GetMapping(FIND_SALE_BY_CODE_SALE)
    @Operation(
            summary = "Find out a sale by code_category",
            description = "This method allow to find out a sale with code_sale",
            tags = {"SaleDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Sale was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing sale exist in DB with code_sale provided")
    })
    ResponseEntity<SaleDto> getCodeSale (@PathVariable(name = "idCodeSale") String codeSale);
    @GetMapping
    @Operation(
            summary = "Return list of sales",
            description = "This method allow to research and return all sales that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All sales were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SaleDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<SaleDto>> listSale();
    @DeleteMapping(DELETE_SALE)
    @Operation(
            summary = "Delete a sale",
            description = "This method allow to delete a sale by ID",
            tags = {"SaleDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Sale has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<?> deleteSale (@PathVariable(name = "idSale") Long id);
}
