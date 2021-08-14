package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.cbums.service.exception.NotAcceptMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/")
    public void registerMember(HttpServletResponse response,
                               @CookieValue(value = "form-id", required = false) Cookie formCookie,
                               @RequestBody JoinForWriteFormParameter joinForWriteFormParameter) throws IOException {
        Member member = new Member();
        member.setName(joinForWriteFormParameter.getName());
        member.setNickName(joinForWriteFormParameter.getNickName());
        member.setEmail(joinForWriteFormParameter.getEmail());
        member.setPhoneNumber(joinForWriteFormParameter.getPhoneNumber());
        member.setDepartment(joinForWriteFormParameter.getDepartment());
        member.setClassNumber(joinForWriteFormParameter.getClassNumber());
        memberService.joinForWriteForm(member);

        if (formCookie != null) {
            String formId = formCookie.getValue();
            formCookie.setPath("/");
            formCookie.setMaxAge(0);
            response.addCookie(formCookie);

            response.sendRedirect("/form/" + formId);
        } else {
            response.sendRedirect("/");
        }

    }

    @GetMapping("/register/check")
    public String checkAcceptMemberPage() {
        return "/member/check";
    }

    @PostMapping("/register/check")
    public void checkAcceptMember(HttpServletResponse response,
                                  @RequestParam String email) throws NotAcceptMemberException, IOException {
        memberService.checkAcceptMember(email);
        response.sendRedirect("/member/detail");
    }


    //patch를 여러개로 분할 //TODO

    //가입승인자 정보 추가 페이지
    @GetMapping("/detail")
    public String detailPage() {
        return "/member/detail";
    }

    @PatchMapping("/detail")
    public void addMemberDetail(HttpServletResponse response,
                                @RequestBody SignUpFormParameter signUpFormParameter) throws IOException {
        memberService.setMemberDetail(signUpFormParameter);
        response.sendRedirect("/");
    }

    // 아이디 & 비밀번호 찾기 페이지
    @GetMapping("/forgot")
    public String forgotPage() {
        return "/member/forgot";
    }

    @GetMapping("member/forgot/email")
    public String forgotEmail(HttpServletResponse response,
                              @RequestParam Integer classNumber) {
        return memberService.getBlindMemberEmail(classNumber);
    }

    @GetMapping("member/forgot/password")
    public String forgotPassword(@RequestParam String email) {
        memberService.setTemporaryPassword(email);

        return "/member/forgot/password";
    }

    //정보 수정 페이지
    @GetMapping("/update")
    public String updatePage() {
        return "/member/update";
    }

    @GetMapping("/register")
    public String registerPage() {

        return "/register";
    }

}
