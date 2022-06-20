package com.inventor.management.web.api;


import com.inventor.management.dto.CustomerDto;
import com.inventor.management.routes.endpoint.CustomerEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = CustomerEndPoint.CUSTOMER_ENDPOINT)
public interface CustomerApi {

    @PostMapping(value = CustomerEndPoint.CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save customer", notes = "This method allow to save customer", response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Customer objet has invalid")
    })
    CustomerDto saveCustomer (@RequestBody CustomerDto customerDto);

    @PutMapping(value = CustomerEndPoint.CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Customer",notes = "This method allow to update customer",response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Customer objet has invalid")
    })
    CustomerDto updateCustomer (@RequestBody CustomerDto customerDto);

    @GetMapping(value = CustomerEndPoint.FIND_CUSTOMER_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out a customer by ID",
            notes = "This method allow to find out a customer with ID",response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing customer exist in DB with ID provided")
    })
    CustomerDto getCustomer (@PathVariable(name = "idCustomer") Long id);

    @GetMapping(value = CustomerEndPoint.CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return list of customers",
            notes = "This method allow to research and return all customers that exist in DB",responseContainer = "List<CustomerDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All customers were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<CustomerDto> listCustomer ();

    @DeleteMapping(value = CustomerEndPoint.DELETE_CUSTOMER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a customer",
            notes = "This method allow to delete a customer by ID",response = CustomerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Customer has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteCustomer (@PathVariable(name = "idCustomer") Long id);
}
