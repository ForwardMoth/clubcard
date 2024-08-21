package com.example.clubcard.controller;

import com.example.clubcard.controller.api.HelloApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController implements HelloApi {

    @GetMapping
    public String get(){
        return "Hello, world";
    }
}
