package com.cbums.controller;

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
@RequestMapping("/admin/**")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final FormService formService;

    @GetMapping("/")
    public String adminPage() {

        return "/admin/admin-menu";
    }

    @GetMapping("/member")
    public List<Member> memberViewPage() {
        return memberService.getMembers();
    }

    @GetMapping("/form")
    public List<Form> formViewPage() {
        return formService.getForms();
    }
}
