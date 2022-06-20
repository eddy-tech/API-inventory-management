package com.inventor.management.web.api;

import com.inventor.management.dto.SaleDto;
import com.inventor.management.routes.endpoint.SaleEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = SaleEndPoint.SALE_ENDPOINT)
public interface SaleApi {

    @PostMapping(value = SaleEndPoint.SALE_ENDPOINT)
    @ApiOperation(value = "Save Sale", notes = "This method allow to save sale", response = SaleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Sale objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Sale objet has invalid")
    })
    SaleDto saveSale (@RequestBody SaleDto saleDto);

    @PutMapping(value = SaleEndPoint.SALE_ENDPOINT)
    @ApiOperation(value = "Update Sale",notes = "This method allow to update sale",response = SaleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Sale objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Sale objet has invalid")
    })
    SaleDto updateSale (@RequestBody SaleDto saleDto);

    @GetMapping(value = SaleEndPoint.FIND_SALE_BY_ID)
    @ApiOperation(value = "Find out a sale by ID",
            notes = "This method allow to find out a sale with ID",response = SaleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Sale was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing sale exist in DB with ID provided")
    })
    SaleDto getSale (@PathVariable(name = "idSale") Long id);

    @GetMapping(value = SaleEndPoint.FIND_SALE_BY_CODE_SALE)
    @ApiOperation(value = "Find out a sale by code_category",
            notes = "This method allow to find out a sale with code_sale",response = SaleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sale was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing sale exist in DB with code_sale provided")
    })
    SaleDto getCodeSale (@PathVariable(name = "idCodeSale") String codeSale);

    @GetMapping(value = SaleEndPoint.SALE_ENDPOINT)
    @ApiOperation(value = "Return list of sales",
            notes = "This method allow to research and return all sales that exist in DB",responseContainer = "List<SaleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All sales were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<SaleDto> listSale ();

    @DeleteMapping(value = SaleEndPoint.DELETE_SALE)
    @ApiOperation(value = "Delete a sale",
            notes = "This method allow to delete a sale by ID",response = SaleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Sale has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteSale (@PathVariable(name = "idSale") Long id);
}
