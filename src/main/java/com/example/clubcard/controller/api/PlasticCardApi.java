package com.example.clubcard.controller.api;

import com.example.clubcard.domain.dto.plastic_card.PlasticCardRequest;
import com.example.clubcard.domain.dto.plastic_card.PlasticCardResponse;
import com.example.clubcard.exception.message.record.BadRequestMessage;
import com.example.clubcard.exception.message.record.ForbiddenMessage;
import com.example.clubcard.exception.message.record.UnauthorizedMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;


@Tag(name = "Plastic card endpoints")
public interface PlasticCardApi {
    @Operation(summary = "Get all plastic cards with pagination, filters and sorting (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting plastic card info",
                    content = {@Content(
                            schema = @Schema(implementation = Page.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(
                            schema = @Schema(implementation = ForbiddenMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<Page<PlasticCardResponse>> getAllPlasticCards(@RequestParam(defaultValue = "0")
                                                                 @Min(0)
                                                                 Integer offset,
                                                                 @RequestParam(defaultValue = "10")
                                                                 @Min(1)
                                                                 Integer limit,
                                                                 @RequestParam(defaultValue = "PROGRESS", required = false)
                                                                 String status,
                                                                 @RequestParam(required = false)
                                                                 Instant createdAt,
                                                                 @RequestParam(required = false)
                                                                 Long cardTypeId,
                                                                 @RequestParam(required = false, defaultValue = "id")
                                                                 String sortBy,
                                                                 @RequestParam(required = false, defaultValue = "ASC")
                                                                 Sort.Direction sortDirection);

    @Operation(summary = "Get plastic card info (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting plastic card info",
                    content = {@Content(
                            schema = @Schema(implementation = PlasticCardResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(
                            schema = @Schema(implementation = ForbiddenMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<PlasticCardResponse> getPlasticCard(@PathVariable Long id);

    @Operation(summary = "Create card (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success creating card",
                    content = {@Content(
                            schema = @Schema(implementation = PlasticCardResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<PlasticCardResponse> createCard(@PathVariable Long userId,
                                                   @RequestBody PlasticCardRequest request);

    @Operation(summary = "Update card (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success update card",
                    content = {@Content(
                            schema = @Schema(implementation = PlasticCardResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<PlasticCardResponse> updateCard(@PathVariable Long userId,
                                                   @RequestBody PlasticCardRequest request);


    @Operation(summary = "Update ready status card (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success update ready status",
                    content = {@Content(
                            schema = @Schema(implementation = PlasticCardResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(
                            schema = @Schema(implementation = ForbiddenMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<PlasticCardResponse> updateStatusCard(@PathVariable Long userId);

    @Operation(summary = "Delete card (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success delete card",
                    content = {@Content(
                            schema = @Schema(implementation = PlasticCardResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = {@Content(
                            schema = @Schema(implementation = BadRequestMessage.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {@Content(
                            schema = @Schema(implementation = UnauthorizedMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<Void> deleteCard(@PathVariable Long userId);
}
