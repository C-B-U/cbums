package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final HttpServletRequest request;

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
            Long memberId = memberService.getAcceptMember(email);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("signUpUserId", memberId);
            return "redirect:/member/sign-up";
        }catch (NullPointerException e) {
            return "/";
        }
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        HttpSession httpSession = request.getSession();
        Long memberId = Optional
                .of((Long)httpSession.getAttribute("signUpUserId"))
                .orElseThrow(NullPointerException::new);

        return "/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(SignUpFormParameter signUpFormParameter) {

        return "/default";
    }
}
