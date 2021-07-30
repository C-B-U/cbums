package com.cbums.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/form/**")
public class FormController {

    @GetMapping("/")
    public String formPage() {

        return "/form";
    }
}
