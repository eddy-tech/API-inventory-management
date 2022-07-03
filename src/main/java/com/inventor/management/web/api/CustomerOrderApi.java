package com.inventor.management.web.api;

import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.dto.CustomerOrderLineDto;
import com.inventor.management.enums.StateOrder;
import com.inventor.management.routes.endpoint.CustomerOrderEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Api(value = CustomerOrderEndPoint.CUSTOMER_ORDER_ENDPOINT)
public interface CustomerOrderApi {

    @PostMapping(value = CustomerOrderEndPoint.CUSTOMER_ORDER_ENDPOINT)
    @ApiOperation(value = "Save customer order", notes = "This method allow to save customer order", response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer Order objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Customer Order objet has invalid")
    })
    ResponseEntity<CustomerOrderDto> saveCustomerOrder (@RequestBody CustomerOrderDto customerOrderDto);

    @PutMapping(value = CustomerOrderEndPoint.CUSTOMER_ORDER_ENDPOINT)
    @ApiOperation(value = "Update Customer Order",notes = "This method allow to update customer order",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer Order objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Customer Order objet has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateCustomerOrder (@RequestBody CustomerOrderDto customerOrderDto);

    @PatchMapping(value = CustomerOrderEndPoint.UPDATE_STATE_ORDER)
    @ApiOperation(value = "Updater State Order", notes = "This method allow to update state order customer", response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "State order object has been updated"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "state customer order has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateStateOrder (@PathVariable(name = "idOrder") Long orderId,
                                                       @PathVariable(name = "stateOrder") StateOrder stateOrder);

    @PatchMapping(value = CustomerOrderEndPoint.UPDATE_QUANTITY_ORDER)
    @ApiOperation(value = "Update State Order", notes = "This method allow to update state order customer", response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "State order object has been updated"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "state customer order has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateQuantityOrdered (@PathVariable(name = "idOrder") Long orderId,
                                                            @PathVariable(name = "idOrderLine")Long orderLineId,
                                                            @PathVariable(name = "quantity") BigDecimal quantity);

    @PatchMapping(value = CustomerOrderEndPoint.UPDATE_CUSTOMER)
    @ApiOperation(value = "Update State customer", notes = "This method allow to update state customer", response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "State customer object has been updated"),
            @ApiResponse(code = 403, message = "Unauthorized access for this object"),
            @ApiResponse(code = 404, message = "state customer has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateCustomer (@PathVariable(name = "idOrder") Long orderId,
                                                     @PathVariable(name = "idCustomer") Long customerId);

    @PatchMapping(value = CustomerOrderEndPoint.UPDATE_ARTICLE)
    @ApiOperation(value = "Update State article", notes = "This method allow to update state article", response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "State article object has been updated"),
            @ApiResponse(code = 403, message = "Unauthorized access for this object"),
            @ApiResponse(code = 404, message = "state article has invalid")
    })
    ResponseEntity<CustomerOrderDto> updateArticle (@PathVariable(name = "idOrder") Long orderId,
                                                    @PathVariable(name = "idOrderLine")Long orderLineId,
                                                    @PathVariable(name = "idArticle")Long articleId);

    @GetMapping(value = CustomerOrderEndPoint.FIND_CUSTOMER_ORDER_BY_ID)
    @ApiOperation(value = "Find out a customer order by ID",
            notes = "This method allow to find out a customer order with ID",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer Order was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing customer order exist in DB with ID provided")
    })
    ResponseEntity<CustomerOrderDto> getCustomerOrder (@PathVariable(name = "idCustomerOrder") Long id);

    @GetMapping(value = CustomerOrderEndPoint.FIND_CUSTOMER_ORDER_BY_CODE_CUSTOMER_ORDER)
    @ApiOperation(value = "Find out a customer order by code_article",
            notes = "This method allow to find out a customer order with code_customer_order",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer Order was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing customer order exist in DB with code_customer_order customer order")
    })
    ResponseEntity<CustomerOrderDto> getCodeCustomerOrder (@PathVariable(name = "codeOrder")String codeCustomerOrder);

    @GetMapping(value = CustomerOrderEndPoint.CUSTOMER_ORDER_ENDPOINT)
    @ApiOperation(value = "Return list of customers orders",
            notes = "This method allow to research and return all customers orders that exist in DB",responseContainer = "List<CustomerOrderDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All customers orders were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity<List<CustomerOrderDto>> listCustomerOrder ();

    @GetMapping(value = CustomerOrderEndPoint.FIND_CUSTOMER_ORDER_LINE_BY_CUSTOMER_ORDER_ID)
    @ApiOperation(value = "Return list of customers orders line with customer order id",
            notes = "This method allow to research and return all customers orders that exist in DB",responseContainer = "List<CustomerOrderLineDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All customers orders line with customer order id were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity<List<CustomerOrderLineDto>> findAllCustomerOrdersLinesByCustomerOrderId (@PathVariable(name = "idOrder") Long orderId);

    @DeleteMapping(value = CustomerOrderEndPoint.DELETE_CUSTOMER_ORDER)
    @ApiOperation(value = "Delete a customer order",
            notes = "This method allow to delete a customer order by ID",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer Order has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity<Void> deleteCustomerOrder (@PathVariable(name = "idCustomerOrder") Long id);

    @DeleteMapping(value = CustomerOrderEndPoint.DELETE_ARTICLE)
    @ApiOperation(value = "Delete a customer order article line",
            notes = "This method allow to delete a customer order article line by ID",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer order article line has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity<CustomerOrderDto> deleteArticle (@PathVariable(name = "idOrder")Long orderId,
                                                    @PathVariable(name = "idOrderLine")Long orderLineId);
}
