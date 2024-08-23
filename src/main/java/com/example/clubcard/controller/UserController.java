package com.example.clubcard.controller;

import com.example.clubcard.controller.api.UserApi;
import com.example.clubcard.domain.dto.response.user.UserBalanceResponse;
import com.example.clubcard.domain.dto.response.user.UserProfileResponse;
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
    public ResponseEntity<?> getStatus(@PathVariable Long id){
        return ResponseEntity.ok("123");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id,
                                        @RequestHeader String auth){
        return ResponseEntity.ok("123");
    }
}

