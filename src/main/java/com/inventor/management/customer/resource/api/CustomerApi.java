package com.inventor.management.customer.resource.api;

import com.inventor.management.customer.dto.CustomerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.customer.roots.CustomerEndPoint.*;

public interface CustomerApi {
    @PostMapping(
            value = CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Save customer",
            description = "This method allow to save customer",
            tags = {"CustomerDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Customer objet has invalid")
    })
    CustomerDto saveCustomer (@RequestBody CustomerDto customerDto);
    @PutMapping(
            value = UPDATE_CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update Customer",
            description = "This method allow to update customer",
            tags = {"CustomerDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Customer objet has invalid")
    })
    CustomerDto updateCustomer (@PathVariable(name = "idCustomer") Long customerId, @RequestBody CustomerDto customerDto);
    @GetMapping(
            value = FIND_CUSTOMER_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out a customer by ID",
            description = "This method allow to find out a customer with ID",
            tags = {"CustomerDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing customer exist in DB with ID provided")
    })
    CustomerDto getCustomer (@PathVariable(name = "idCustomer") Long id);
    @GetMapping(
            value = CUSTOMER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return list of customers",
            description = "This method allow to research and return all customers that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All customers were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CustomerDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<CustomerDto> listCustomer();

    @DeleteMapping(
            value = DELETE_CUSTOMER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Delete a customer",
            description = "This method allow to delete a customer by ID",
            tags = {"CustomerDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Customer has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    void deleteCustomer (@PathVariable(name = "idCustomer") Long id);
}
