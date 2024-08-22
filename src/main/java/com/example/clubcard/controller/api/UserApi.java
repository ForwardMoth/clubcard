package com.example.clubcard.controller.api;

import com.example.clubcard.domain.dto.response.UserProfileResponse;
import com.example.clubcard.exception.CustomException;
import com.example.clubcard.exception.message.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

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

    @Operation(summary = "Personal information about user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting profile",
                    content = { @Content(
                            schema = @Schema(implementation = UserProfileResponse.class),
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
    ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long id,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String auth);
}
