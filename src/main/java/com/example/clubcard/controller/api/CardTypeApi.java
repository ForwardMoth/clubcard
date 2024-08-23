package com.example.clubcard.controller.api;

import com.example.clubcard.domain.dto.request.card_type.CardTypeRequest;
import com.example.clubcard.domain.dto.response.card_type.CardTypeResponse;
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

@Tag(name = "CardType endpoints")
public interface CardTypeApi {

    @Operation(summary = "All card types (auth)")
    @ApiResponse(
            responseCode = "200",
            description = "List with all card types",
            content = { @Content(schema = @Schema(
                    implementation = List.class),
                    mediaType = "application/json"
            ) }
    )
    ResponseEntity<List<CardTypeResponse>> getAllCardTypes();

    @Operation(summary = "CardType (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting card type",
                    content = { @Content(schema = @Schema(
                            implementation = CardTypeResponse.class),
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
    ResponseEntity<CardTypeResponse> getCardType(@PathVariable Long id);

    @Operation(summary = "Create card type (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success creating card type",
                    content = { @Content(
                            schema = @Schema(implementation = CardTypeResponse.class),
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
    ResponseEntity<CardTypeResponse> createCardType(@RequestBody @Valid CardTypeRequest request);

    @Operation(summary = "Update card type (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating card type",
                    content = { @Content(
                            schema = @Schema(implementation = CardTypeResponse.class),
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
    ResponseEntity<CardTypeResponse> updateCardType(@PathVariable Long id,
                                                      @RequestBody @Valid CardTypeRequest request);


    @Operation(summary = "Delete card type (ADMIN ACCESS)")
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
    ResponseEntity<Void> deleteCardType(@PathVariable Long id);
}
