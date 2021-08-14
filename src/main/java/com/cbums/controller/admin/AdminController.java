package com.cbums.controller.admin;

import com.cbums.model.Form;
import com.cbums.model.Member;
import com.cbums.service.FormService;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("")
    public String adminPage() {

        return "/admin";
    }

}
