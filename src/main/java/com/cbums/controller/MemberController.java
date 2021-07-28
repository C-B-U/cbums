package com.cbums.controller;

import com.cbums.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/**")
public class MemberController {

    @PostMapping("/join-for-write-form")
    public String joinForWriteForm() {
        return "/";
    }

    @PostMapping("/sign-up")
    public String signUp() {

        return "/";
    }
}
