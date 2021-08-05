package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.cbums.service.exception.NotAcceptMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join-for-write-form")
    public String joinForWriteForm(JoinForWriteFormParameter joinForWriteFormParameter) {
        Member member = new Member();
        member.setName(joinForWriteFormParameter.getName());
        member.setNickName(joinForWriteFormParameter.getNickName());
        member.setEmail(joinForWriteFormParameter.getEmail());
        member.setDepartment(joinForWriteFormParameter.getDepartment());
        member.setClassNumber(joinForWriteFormParameter.getClassNumber());
        memberService.joinForWriteForm(member);
        return "/default";
    }

    @PostMapping("/check-accept-sign-up")
    public String checkAcceptSignUp(String email) {
        try {
            memberService.checkAcceptMember(email);

            return "redirect:/member/sign-up-form";
        }catch (NotAcceptMemberException e) {
            return "/denied";
        }
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
    public String signUpForm(SignUpFormParameter signUpFormParameter) {

        memberService.setMemberOtherInfo(signUpFormParameter);

        return "/default";
    }
}
