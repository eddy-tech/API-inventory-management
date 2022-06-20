package com.inventor.management.web.api;

import com.inventor.management.dto.CustomerDto;
import com.inventor.management.dto.ProviderDto;
import com.inventor.management.routes.endpoint.ProviderEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = ProviderEndPoint.PROVIDER_ENDPOINT)
public interface ProviderApi {

    @PostMapping(value = ProviderEndPoint.PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save provider", notes = "This method allow to save provider", response = ProviderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Provider objet has invalid")
    })
    ProviderDto saveProvider (@RequestBody ProviderDto providerDto);

    @PutMapping(value = ProviderEndPoint.PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Provider",notes = "This method allow to update provider",response = ProviderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider objet has been updated"),
            @ApiResponse(code = 403,message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404,message = "Provider objet has invalid")
    })
    ProviderDto updateProvider (@RequestBody ProviderDto providerDto);

    @GetMapping(value = ProviderEndPoint.FIND_PROVIDER_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find out a provider by ID",
            notes = "This method allow to find out a provider with ID",response = ProviderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing provider exist in DB with ID provided")
    })
    ProviderDto getProvider (@PathVariable(name = "idProvider") Long id);

    @GetMapping(value = ProviderEndPoint.PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Return list of providers",
            notes = "This method allow to research and return all providers that exist in DB",responseContainer = "List<ProviderDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All providers were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<ProviderDto> listProviders ();

    @DeleteMapping(value = ProviderEndPoint.DELETE_PROVIDER,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a provider",
            notes = "This method allow to delete a provider by ID",response = ProviderDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Provider has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteProvider (@PathVariable(name = "idProvider") Long id);
}
