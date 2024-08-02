package com.inventor.management.inventor_management.provider.resource.api;

import com.inventor.management.inventor_management.provider.dto.ProviderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.inventor_management.provider.roots.ProviderEndPoint.*;

public interface ProviderApi {
    @PostMapping(
            value = PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Save provider",
            description = "This method allow to save provider",
            tags = {"ProviderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Provider objet has invalid")
    })
    ProviderDto saveProvider (@RequestBody ProviderDto providerDto);
    @PutMapping(
            value = UPDATE_PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update Provider",
            description = "This method allow to update provider",
            tags = {"ProviderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider objet has been updated"),
            @ApiResponse(responseCode = "403",description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404",description = "Provider objet has invalid")
    })
    ProviderDto updateProvider (
            @PathVariable(name = "idProvider") Long providerId,
            @RequestBody ProviderDto providerDto
    );

    @GetMapping(
            value = FIND_PROVIDER_BY_ID,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Find out a provider by ID",
            description = "This method allow to find out a provider with ID",
            tags = {"ProviderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing provider exist in DB with ID provided")
    })
    ProviderDto getProvider (@PathVariable(name = "idProvider") Long id);
    @GetMapping(
            value = PROVIDER_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Return list of providers",
            description = "This method allow to research and return all providers that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All providers were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProviderDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<ProviderDto> listProviders ();
    @DeleteMapping(
            value = DELETE_PROVIDER,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Delete a provider",
            description = "This method allow to delete a provider by ID",
            tags = {"ProviderDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Provider has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    void deleteProvider (@PathVariable(name = "idProvider") Long id);
}
