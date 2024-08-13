package com.inventor.management.inventor_management.providerOrder.resource.api;

import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderDto;
import com.inventor.management.inventor_management.providerOrder.dto.ProviderOrderRequest;
import com.inventor.management.inventor_management.providerOrderLine.dto.ProviderOrderLineDto;
import com.inventor.management.inventor_management.core.enums.StateOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.inventor.management.inventor_management.providerOrder.roots.ProviderOrderEndPoint.*;

public interface ProviderOrderApi {
    @PostMapping
    @Operation(
            summary = "Save provider order",
            description = "This method allow to save provider order",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider Order objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Provider Order objet has invalid")
    })
    ResponseEntity<ProviderOrderDto> saveProviderOrder (@RequestBody @Valid ProviderOrderRequest providerOrderRequest);
    @PutMapping(UPDATE_PROVIDER_ORDER_ENDPOINT)
    @Operation(
            summary = "Update Provider Order",
            description = "This method allow to update provider order",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider Order objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Provider Order objet has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateProviderOrder (
            @PathVariable(name = "idProviderOrder") Long providerOrderId,
            @RequestBody @Valid ProviderOrderRequest providerOrderRequest
    );
    @PatchMapping(UPDATE_STATE_ORDER)
    @Operation(
            summary = "Updater State Order",
            description = "This method allow to update state order provider",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State order object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "state provider order has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateStateOrder (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "stateOrder") StateOrder stateOrder
    );
    @PatchMapping(UPDATE_QUANTITY_ORDER)
    @Operation(
            summary = "Update State Order",
            description = "This method allow to update state order provider",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State order object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "state provider order has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateQuantityOrdered (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId,
            @RequestParam BigDecimal quantity
    );
    @PatchMapping(UPDATE_PROVIDER)
    @Operation(
            summary = "Update State provider",
            description = "This method allow to update state provider",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State provider object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this object"),
            @ApiResponse(responseCode = "404", description = "state provider has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateProvider (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idProvider") Long customerId
    );
    @PatchMapping(UPDATE_ARTICLE)
    @Operation(
            summary = "Update State article",
            description = "This method allow to update state article",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State article object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this object"),
            @ApiResponse(responseCode = "404", description = "state article has invalid")
    })
    ResponseEntity<ProviderOrderDto> updateArticle (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId,
            @PathVariable(name = "idArticle")Long articleId
    );
    @GetMapping(FIND_PROVIDER_ORDER_BY_ID)
    @Operation(
            summary = "Find out a provider order by ID",
            description = "This method allow to find out a provider order with ID",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider Order was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing provider order exist in DB with ID provided")
    })
    ResponseEntity<ProviderOrderDto> getProviderOrder (@PathVariable(name = "idProviderOrder") Long id);
    @GetMapping(FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER)
    @Operation(
            summary = "Find out a provider order by code_article",
            description = "This method allow to find out a provider order order with code_provider_order",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Provider Order was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing provider order exist in DB with code_provider_order provider order")
    })
    ResponseEntity<ProviderOrderDto> getCodeProviderOrder (@PathVariable(name = "codeOrder")String codeProviderOrder);
    @GetMapping(FIND_PROVIDER_ORDER_LINE_BY_PROVIDER_ORDER_ID)
    @Operation(
            summary = "Return list of customers orders line with provider order id",
            description = "This method allow to research and return all providers orders that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All providers orders line with provider order id were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProviderOrderDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<ProviderOrderLineDto>> findAllProviderOrdersLinesByProviderOrderId(
            @PathVariable(name = "idOrder") Long orderId
    );
    @GetMapping
    @Operation(
            summary = "Return list of providers orders",
            description = "This method allow to research and return all providers orders that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All providers orders were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProviderOrderDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<ProviderOrderDto>> listProviderOrder ();
    @DeleteMapping(DELETE_PROVIDER_ORDER)
    @Operation(
            summary = "Delete a provider order",
            description = "This method allow to delete a provider order by ID",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider Order has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<?> deleteProviderOrder (@PathVariable(name = "idProviderOrder") Long id);
    @DeleteMapping(DELETE_ARTICLE)
    @Operation(
            summary = "Delete a provider order article line",
            description = "This method allow to delete a provider order article line by ID",
            tags = {"ProviderOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider order article line has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<?> deleteArticle (
            @PathVariable(name = "idOrder")Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId
    );
}
