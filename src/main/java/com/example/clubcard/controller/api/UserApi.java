package com.example.clubcard.controller.api;

import com.example.clubcard.exception.message.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User's endpoint")
public interface UserApi {

    @Operation(summary = "Access for authorized users")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success hello message",
                    content = { @Content(
                            schema = @Schema(implementation = String.class),
                            mediaType = "application/text"
                    ) }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = { @Content(
                            schema = @Schema(implementation = ErrorMessage.class),
                            mediaType = "application/json"
                    ) }
            )
    })
    String get();
}
