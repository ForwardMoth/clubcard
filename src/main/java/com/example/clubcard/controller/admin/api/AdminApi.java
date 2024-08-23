package com.example.clubcard.controller.admin.api;


import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.response.privilege.PrivilegeResponse;
import com.example.clubcard.exception.message.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Admin's endpoint")
public interface AdminApi {

    @Operation(summary = "Create privilege")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success hello message",
                    content = { @Content(
                            schema = @Schema(implementation = PrivilegeResponse.class),
                            mediaType = "application/json"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            )
    })
    ResponseEntity<PrivilegeResponse> createPrivilege(@RequestBody @Valid PrivilegeRequest request);
}
