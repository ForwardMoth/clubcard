package com.example.clubcard.controller;

import com.example.clubcard.controller.api.UserApi;
import com.example.clubcard.domain.dto.request.privilege.PrivilegeRequest;
import com.example.clubcard.domain.dto.request.user.UserUpdateRequest;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
import com.example.clubcard.domain.dto.response.user.UserResponse;
import com.example.clubcard.domain.dto.response.user.UserStatusResponse;
import com.example.clubcard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long id){
        return ResponseEntity.ok(userService.getProfile(id));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<UserBalanceResponse> getBalance(@PathVariable Long id){
        return ResponseEntity.ok(userService.getBalance(id));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<UserStatusResponse> getStatus(@PathVariable Long id){
        return ResponseEntity.ok(userService.getStatus(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/{id}/update/profile")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        return ResponseEntity.ok(userService.updateProfile(id, request));
    }

    @PostMapping("/{id}/update/blocked")
    public ResponseEntity<UserStatusResponse> updateBlockStatus(@PathVariable Long id){
        return ResponseEntity.ok(userService.updateStatus(id));
    }

    @PostMapping("/{id}/update/privilege")
    public ResponseEntity<?> updatePrivilege(@PathVariable Long id, @RequestBody PrivilegeRequest request){
        return ResponseEntity.ok("123");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok("123");
    }
}

