package com.cbums.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutPage() {
        return ResponseEntity.created(URI.create("/")).build();
    }
}
