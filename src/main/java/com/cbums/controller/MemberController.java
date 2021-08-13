package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.cbums.service.exception.NotAcceptMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join-for-write-form")
    public void joinForWriteForm(HttpServletResponse response, JoinForWriteFormParameter joinForWriteFormParameter) throws IOException {
        Member member = new Member();
        member.setName(joinForWriteFormParameter.getName());
        member.setNickName(joinForWriteFormParameter.getNickName());
        member.setEmail(joinForWriteFormParameter.getEmail());
        member.setDepartment(joinForWriteFormParameter.getDepartment());
        member.setClassNumber(joinForWriteFormParameter.getClassNumber());
        memberService.joinForWriteForm(member);
        response.sendRedirect("/default");
    }

    @PostMapping("/check-accept-sign-up")
    public void checkAcceptSignUp(HttpServletResponse response, String email) throws NotAcceptMemberException, IOException {
        memberService.checkAcceptMember(email);
        response.sendRedirect("/member/sign-up-form");
    }

    @GetMapping("/sign-up")
    public String signUpPage() {

        return "/member/sign-up";
    }

    @GetMapping("/sign-up-form")
    public String signUpFormPage(){
        return "/member/sign-up-form";
    }

    @PostMapping("/sign-up-form")
    public void signUpForm(HttpServletResponse response, SignUpFormParameter signUpFormParameter) throws IOException {

        memberService.setMemberOtherInfo(signUpFormParameter);
        response.sendRedirect("/default");
    }
}
