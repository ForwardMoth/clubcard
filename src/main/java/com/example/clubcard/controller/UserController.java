package com.example.clubcard.controller;

import com.example.clubcard.controller.api.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController implements UserApi {

    @GetMapping
    public String get() {
        return "Hello, user!";
    }
}

