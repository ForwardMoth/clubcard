package com.example.clubcard.controller;

import com.example.clubcard.controller.api.UserApi;
import com.example.clubcard.domain.dto.request.privilege.PrivilegeIdRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import com.example.clubcard.domain.dto.response.user.UserStatusResponse;
import com.example.clubcard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getProfile(id));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<UserBalanceResponse> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getBalance(id));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<UserStatusResponse> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getStatus(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PatchMapping("/{id}/update/profile")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long id,
                                                      @RequestBody @Valid UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateProfile(id, request));
    }

    @PatchMapping("/{id}/update/blocked")
    public ResponseEntity<UserStatusResponse> updateBlockStatus(@PathVariable Long id) {
        return ResponseEntity.ok(userService.updateStatus(id));
    }

    @PatchMapping("/{id}/update/privilege")
    public ResponseEntity<UserResponse> updatePrivilege(@PathVariable Long id,
                                                        @RequestBody @Valid PrivilegeIdRequest request) {
        return ResponseEntity.ok(userService.updatePrivilege(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

