package com.inventor.management.enterprise.resource.api;

import com.inventor.management.enterprise.dto.EnterpriseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.inventor.management.enterprise.roots.EnterpriseEndPoint.*;

public interface EnterpriseApi {
    @PostMapping(value = ENTERPRISE_ENDPOINT)
    @Operation(
            summary = "Save Enterprise",
            description = "This method allow to save an enterprise",
            tags = {"EnterpriseDto"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enterprise objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Enterprise objet has invalid")
    })
    EnterpriseDto saveEnterprise (@RequestBody EnterpriseDto enterpriseDto);
    @PutMapping(value = UPDATE_ENTERPRISE_ENDPOINT)
    @Operation(
            summary = "Update Enterprise",
            description = "This method allow to save an enterprise",
            tags = {"EnterpriseDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200", description = "Enterprise objet has been saved"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "400", description = "Enterprise objet has invalid")
    })
    EnterpriseDto updateEnterprise (
            @PathVariable(name = "idEnterprise") Long enterpriseId,
            @RequestBody EnterpriseDto enterpriseDto);

    @GetMapping(value = FIND_ENTERPRISE_BY_ID)
    @Operation(
            summary = "Find out an enterprise by ID",
            description = "This method allow to find out an enterprise with ID",
            tags = {"EnterpriseDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Enterprise was found in DB"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet"),
            @ApiResponse(responseCode = "404", description = "Nothing enterprise exist in DB with ID provided")
    })
    EnterpriseDto getEnterprise (@PathVariable(name = "idEnterprise") Long id);
    @GetMapping(value = ENTERPRISE_ENDPOINT)
    @Operation(
            summary = "Return list of enterprises",
            description = "This method allow to research and return all enterprises that exist in DB"
    )
    @ApiResponses(
            value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All enterprises were found in DB / Empty list",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EnterpriseDto.class))
                            )
                    }
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    List<EnterpriseDto> listEnterprise ();
    @DeleteMapping(value = DELETE_ENTERPRISE)
    @Operation(
            summary = "Delete an enterprise",
            description = "This method allow to delete an enterprise by ID",
            tags = {"EnterpriseDto"}
    )
    @ApiResponses(
            value = {
            @ApiResponse(responseCode = "200",description = "Enterprise has been deleted"),
            @ApiResponse(responseCode = "403", description = "Unauthorized access for this objet")
    })
    void deleteEnterprise (@PathVariable(name = "idEnterprise") Long id);
}
