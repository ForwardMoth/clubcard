package com.example.clubcard.controller;

import com.example.clubcard.controller.api.UserApi;
import com.example.clubcard.domain.dto.response.UserProfileResponse;
import com.example.clubcard.service.UserService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @GetMapping
    public String get() {
        return "Hello, user!";
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@PathVariable Long id,
                                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        return ResponseEntity.ok(userService.getProfile(id, auth));
    }
}

