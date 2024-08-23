package com.example.clubcard.controller.api;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Operation(summary = "Create privilege (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success creating privilege",
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

    @Operation(summary = "Update privilege (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating privilege",
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
    ResponseEntity<PrivilegeResponse> updatePrivilege(@PathVariable Long id,
                                                      @RequestBody @Valid PrivilegeRequest request);


    @Operation(summary = "Delete privilege (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "No content",
                    content = { @Content(
                            schema = @Schema(implementation = Void.class),
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
    ResponseEntity<Void> deletePrivilege(@PathVariable Long id);
}
