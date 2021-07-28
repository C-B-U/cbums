package com.cbums.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String defaultPage() {
        return "/default";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }
}