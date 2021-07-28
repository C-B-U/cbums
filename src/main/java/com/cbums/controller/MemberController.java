package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/**")
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

    @PostMapping("/sign-up")
    public String signUp() {

        return "/";
    }
}
