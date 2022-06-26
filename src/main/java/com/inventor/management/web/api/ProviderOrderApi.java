package com.inventor.management.web.api;

import com.inventor.management.dto.ProviderOrderDto;
import com.inventor.management.roots.ProviderOrderEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = ProviderOrderEndPoint.PROVIDER_ORDER_ENDPOINT)
public interface ProviderOrderApi {

    @PostMapping(value = ProviderOrderEndPoint.PROVIDER_ORDER_ENDPOINT)
    @ApiOperation(value = "Save provider order", notes = "This method allow to save provider order", response = ProviderOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider Order objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Provider Order objet has invalid")
    })
    ResponseEntity<ProviderOrderDto> saveProviderOrder (@RequestBody ProviderOrderDto providerOrderDto);

    @PutMapping(value = ProviderOrderEndPoint.PROVIDER_ORDER_ENDPOINT)
    @ApiOperation(value = "Update Provider Order",notes = "This method allow to update provider order",response = ProviderOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider Order objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Provider Order objet has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateProviderOrder (@RequestBody ProviderOrderDto providerOrderDto);

    @GetMapping(value = ProviderOrderEndPoint.FIND_PROVIDER_ORDER_BY_ID)
    @ApiOperation(value = "Find out a provider order by ID",
            notes = "This method allow to find out a provider order with ID",response = ProviderOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider Order was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing provider order exist in DB with ID provided")
    })
    ResponseEntity<ProviderOrderDto> getProviderOrder (@PathVariable(name = "idProviderOrder") Long id);

    @GetMapping(value = ProviderOrderEndPoint.FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER)
    @ApiOperation(value = "Find out a provider order by code_article",
            notes = "This method allow to find out a provider order order with code_provider_order",response = ProviderOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Provider Order was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing provider order exist in DB with code_provider_order provider order")
    })
    ResponseEntity<ProviderOrderDto> getCodeProviderOrder (@PathVariable(name = "codeOrder")String codeProviderOrder);

    @GetMapping(value = ProviderOrderEndPoint.PROVIDER_ORDER_ENDPOINT)
    @ApiOperation(value = "Return list of providers orders",
            notes = "This method allow to research and return all providers orders that exist in DB",responseContainer = "List<ProviderOrderDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All providers orders were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity<List<ProviderOrderDto>> listProviderOrder ();

    @DeleteMapping(value = ProviderOrderEndPoint.DELETE_PROVIDER_ORDER)
    @ApiOperation(value = "Delete a provider order",
            notes = "This method allow to delete a provider order by ID",response = ProviderOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider Order has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity deleteCustomerOrder (@PathVariable(name = "idProviderOrder") Long id);
}
