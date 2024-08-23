package com.example.clubcard.controller.api;

import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.exception.message.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Privilege endpoints")
public interface PrivilegeApi {

    @Operation(summary = "All privileges")
    @ApiResponse(
            responseCode = "200",
            description = "List with all privileges",
            content = { @Content(schema = @Schema(
                    implementation = List.class),
                    mediaType = "application/json"
            ) }
    )
    ResponseEntity<List<PrivilegeResponse>> getAllPrivileges();

    @Operation(summary = "Privilege")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting privilege",
                    content = { @Content(schema = @Schema(
                            implementation = PrivilegeResponse.class),
                            mediaType = "application/json"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            )
    })
    ResponseEntity<PrivilegeResponse> getPrivilege(@PathVariable Long id);
}
