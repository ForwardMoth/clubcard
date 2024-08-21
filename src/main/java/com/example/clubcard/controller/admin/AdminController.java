package com.example.clubcard.controller.admin;

import com.example.clubcard.controller.admin.api.AdminApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController implements AdminApi {

    @GetMapping
    public String get() {
        return "Hello, admin!";
    }
}
