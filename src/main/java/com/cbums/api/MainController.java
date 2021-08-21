package com.cbums.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MainController {

    //front app에서만 요정하면 될 페이지들은 일단 보류 TODO
    @GetMapping("")
    public String defaultPage() {
        return "/default";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutPage() {
        return ResponseEntity.created(URI.create("/")).build();
    }
}
