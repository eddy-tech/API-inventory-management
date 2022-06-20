package com.inventor.management.web.api;

import com.inventor.management.dto.CustomerOrderDto;
import com.inventor.management.routes.endpoint.CustomerOrderEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(value = CustomerOrderEndPoint.DELETE_CUSTOMER_ORDER)
    @ApiOperation(value = "Delete a customer order",
            notes = "This method allow to delete a customer order by ID",response = CustomerOrderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer Order has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    ResponseEntity deleteCustomerOrder (@PathVariable(name = "idCustomerOrder") Long id);
}
