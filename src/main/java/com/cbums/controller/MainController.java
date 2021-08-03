package com.cbums.controller;


import com.cbums.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping("")
    public String defaultPage() {
        return "/default";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        memberService.logout();
        return "/logout";
    }

    @GetMapping("/denied")
    public String deniedPage() {
        return "/denied";
    }
    @GetMapping("/login-success")
    public String loginSuccessPage() {
        return "/login-success";
    }

    @GetMapping("/form-page")
    public String formPage() {
        return "/form";
    }

}
