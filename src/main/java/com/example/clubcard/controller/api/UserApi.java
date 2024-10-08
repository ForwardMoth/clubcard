package com.example.clubcard.controller.api;

import com.example.clubcard.domain.dto.privilege.PrivilegeIdRequest;
import com.example.clubcard.domain.dto.user.*;
import com.example.clubcard.exception.message.record.BadRequestMessage;
import com.example.clubcard.exception.message.record.ForbiddenMessage;
import com.example.clubcard.exception.message.record.NotFoundMessage;
import com.example.clubcard.exception.message.record.UnauthorizedMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "User endpoints")
public interface UserApi {
    @Operation(summary = "User profile by uuid (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting user profile",
                    content = {@Content(
                            schema = @Schema(implementation = UserResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserResponse> getUserByQrCode(@RequestParam @NotNull String uuid);

    @Operation(summary = "Get user uuid (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting user uuid",
                    content = {@Content(
                            schema = @Schema(implementation = UserQrCodeResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserQrCodeResponse> getQrCode(@PathVariable Long id);

    @Operation(summary = "Get personal user information (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting profile",
                    content = {@Content(
                            schema = @Schema(implementation = UserProfileResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long id);

    @Operation(summary = "Get user balance (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting user balance",
                    content = {@Content(
                            schema = @Schema(implementation = UserBalanceResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserBalanceResponse> getBalance(@PathVariable Long id);

    @Operation(summary = "Get user status and privilege (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting user status and privilege",
                    content = {@Content(
                            schema = @Schema(implementation = UserStatusResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    public ResponseEntity<UserStatusResponse> getStatus(@PathVariable Long id);

    @Operation(summary = "Get full user information (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success getting user information",
                    content = {@Content(
                            schema = @Schema(implementation = UserResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserResponse> getUser(@PathVariable Long id);


    @Operation(summary = "Update personal user information (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating user information",
                    content = {@Content(
                            schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserResponse> updateProfile(@PathVariable Long id,
                                               @RequestBody @Valid UserUpdateRequest request);

    @Operation(summary = "Update user block status (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating block status of user",
                    content = {@Content(
                            schema = @Schema(implementation = UserStatusResponse.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserStatusResponse> updateBlockStatus(@PathVariable Long id);

    @Operation(summary = "Update user privilege (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating user privilege",
                    content = {@Content(
                            schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserResponse> updatePrivilege(@PathVariable Long id,
                                                 @RequestBody @Valid PrivilegeIdRequest request);

    @Operation(summary = "Update user balance (ADMIN)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success updating user privilege",
                    content = {@Content(
                            schema = @Schema(implementation = UserResponse.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
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
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<UserBalanceResponse> updateUserBalance(@PathVariable Long id,
                                                          @RequestBody @Valid UserBalanceRequest request);

    @Operation(summary = "Delete user (auth)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "No content",
                    content = {@Content(
                            schema = @Schema(implementation = Void.class),
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
                    responseCode = "404",
                    description = "Not Found",
                    content = {@Content(
                            schema = @Schema(implementation = NotFoundMessage.class),
                            mediaType = "application/json"
                    )}
            )
    })
    ResponseEntity<Void> deleteUser(@PathVariable Long id);

    @Operation(summary = "Get all users with pagination, filters and sorting (ADMIN ACCESS)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success found users",
                    content = {@Content(
                            schema = @Schema(implementation = Page.class),
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
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
    ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0")
            @Min(0)
            Integer offset,
            @RequestParam(defaultValue = "10")
            @Min(1)
            Integer limit,
            @RequestParam(required = false)
            String lastname,
            @RequestParam(required = false)
            Long privilegeId,
            @RequestParam(required = false)
            Boolean isBlocked,
            @RequestParam(required = false, defaultValue = "id")
            String sortBy,
            @RequestParam(required = false, defaultValue = "ASC")
            Sort.Direction sortDirection
    );
}
