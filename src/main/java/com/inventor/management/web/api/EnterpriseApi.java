package com.inventor.management.web.api;

import com.inventor.management.dto.ArticleDto;
import com.inventor.management.dto.EnterpriseDto;
import com.inventor.management.routes.endpoint.ArticleEndPoint;
import com.inventor.management.routes.endpoint.EnterpriseEndPoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = EnterpriseEndPoint.ENTERPRISE_ENDPOINT)
public interface EnterpriseApi {

    @PostMapping(value = EnterpriseEndPoint.ENTERPRISE_ENDPOINT)
    @ApiOperation(value = "Save Enterprise", notes = "This method allow to save an enterprise", response = EnterpriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Enterprise objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 400, message = "Enterprise objet has invalid")
    })
    EnterpriseDto saveEnterprise (@RequestBody EnterpriseDto enterpriseDto);

    @PutMapping(value = EnterpriseEndPoint.ENTERPRISE_ENDPOINT)
    @ApiOperation(value = "Update Enterprise", notes = "This method allow to save an enterprise", response = EnterpriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Enterprise objet has been saved"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 400, message = "Enterprise objet has invalid")
    })
    EnterpriseDto updateEnterprise (@RequestBody EnterpriseDto enterpriseDto);

    @GetMapping(value = EnterpriseEndPoint.FIND_ENTERPRISE_BY_ID)
    @ApiOperation(value = "Find out an enterprise by ID",
            notes = "This method allow to find out an enterprise with ID", response = EnterpriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Enterprise was found in DB"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet"),
            @ApiResponse(code = 404, message = "Nothing enterprise exist in DB with ID provided")
    })
    EnterpriseDto getEnterprise (@PathVariable(name = "idEnterprise") Long id);

    @GetMapping(value = EnterpriseEndPoint.ENTERPRISE_ENDPOINT)
    @ApiOperation(value = "Return list of enterprises",
            notes = "This method allow to research and return all enterprises that exist in DB",responseContainer = "List<EnterpriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "All enterprises were found in DB / Empty list"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    List<EnterpriseDto> listEnterprise ();

    @DeleteMapping(value = EnterpriseEndPoint.DELETE_ENTERPRISE)
    @ApiOperation(value = "Delete an enterprise",
            notes = "This method allow to delete an enterprise by ID", response = EnterpriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Enterprise has been deleted"),
            @ApiResponse(code = 403, message = "Unauthorized access for this objet")
    })
    void deleteEnterprise (@PathVariable(name = "idEnterprise") Long id);
}
