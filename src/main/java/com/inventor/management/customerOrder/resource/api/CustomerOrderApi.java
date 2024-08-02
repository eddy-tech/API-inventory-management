package com.inventor.management.customerOrder.resource.api;

import com.inventor.management.category.dto.CategoryDto;
import com.inventor.management.customerOrder.dto.CustomerOrderDto;
import com.inventor.management.customerOrder.roots.CustomerOrderEndPoint;
import com.inventor.management.customerOrderLine.dto.CustomerOrderLineDto;
import com.inventor.management.core.enums.StateOrder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.inventor.management.customerOrder.roots.CustomerOrderEndPoint.*;

public interface CustomerOrderApi {
    @PostMapping(value = CUSTOMER_ORDER_ENDPOINT)
    @Operation(
            summary = "Save customer order",
            description = "This method allow to save customer order",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer Order objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Customer Order objet has invalid")
    })
    ResponseEntity<CustomerOrderDto> saveCustomerOrder (@RequestBody CustomerOrderDto customerOrderDto);
    @PutMapping(value = UPDATE_CUSTOMER_ORDER_ENDPOINT)
    @Operation(
            summary = "Update Customer Order",
            description = "This method allow to update customer order",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer Order objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Customer Order objet has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateCustomerOrder (
            @PathVariable(name = "idCustomerOrder") Long customerOrderId,
            @RequestBody CustomerOrderDto customerOrderDto
    );
    @PatchMapping(value = UPDATE_STATE_ORDER)
    @Operation(summary = "Updater State Order", description = "This method allow to update state order customer", tags = {"CustomerOrderDto"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "State order object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "state customer order has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateStateOrder (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "stateOrder") StateOrder stateOrder
    );
    @PatchMapping(value = UPDATE_QUANTITY_ORDER)
    @Operation(
            summary = "Update State Order",
            description = "This method allow to update state order customer",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State order object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "state customer order has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateQuantityOrdered (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId,
            @PathVariable(name = "quantity") BigDecimal quantity
    );
    @PatchMapping(value = UPDATE_CUSTOMER)
    @Operation(
            summary = "Update State customer",
            description = "This method allow to update state customer",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State customer object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this object"),
            @ApiResponse(responseCode = "404", description = "state customer has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateCustomer (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idCustomer") Long customerId
    );
    @PatchMapping(value = UPDATE_ARTICLE)
    @Operation(
            summary = "Update State article",
            description = "This method allow to update state article",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "State article object has been updated"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this object"),
            @ApiResponse(responseCode = "404", description = "state article has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateArticle (
            @PathVariable(name = "idOrder") Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId,
            @PathVariable(name = "idArticle")Long articleId
    );
    @GetMapping(value = FIND_CUSTOMER_ORDER_BY_ID)
    @Operation(
            summary = "Find out a customer order by ID",
            description = "This method allow to find out a customer order with ID",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer Order was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing customer order exist in DB with ID provided")
    })
    ResponseEntity<CustomerOrderDto> getCustomerOrder (@PathVariable(name = "idCustomerOrder") Long id);
    @GetMapping(value = FIND_CUSTOMER_ORDER_BY_CODE_CUSTOMER_ORDER)
    @Operation(
            summary = "Find out a customer order by code_article",
            description = "This method allow to find out a customer order with code_customer_order",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Customer Order was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing customer order exist in DB with code_customer_order customer order")
    })
    ResponseEntity<CustomerOrderDto> getCodeCustomerOrder (@PathVariable(name = "codeOrder")String codeCustomerOrder);
    @GetMapping(value = CUSTOMER_ORDER_ENDPOINT)
    @Operation(
            summary = "Return list of customers orders",
            description = "This method allow to research and return all customers orders that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All customers orders were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerOrderDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<CustomerOrderDto>> listCustomerOrder ();
    @GetMapping(value = FIND_CUSTOMER_ORDER_LINE_BY_CUSTOMER_ORDER_ID)
    @Operation(
            summary = "Return list of customers orders line with customer order id",
            description = "This method allow to research and return all customers orders that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All customers orders line with customer order id were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerOrderDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<List<CustomerOrderLineDto>> findAllCustomerOrdersLinesByCustomerOrderId (@PathVariable(name = "idOrder") Long orderId);
    @DeleteMapping(value = DELETE_CUSTOMER_ORDER)
    @Operation(
            summary = "Delete a customer order",
            description = "This method allow to delete a customer order by ID",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer Order has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<Void> deleteCustomerOrder (@PathVariable(name = "idCustomerOrder") Long id);
    @DeleteMapping(value = DELETE_ARTICLE)
    @Operation(
            summary = "Delete a customer order article line",
            description = "This method allow to delete a customer order article line by ID",
            tags = {"CustomerOrderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer order article line has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    ResponseEntity<CustomerOrderDto> deleteArticle (
            @PathVariable(name = "idOrder")Long orderId,
            @PathVariable(name = "idOrderLine")Long orderLineId
    );
}
