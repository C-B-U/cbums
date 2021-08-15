package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.OverlapDataException;
import lombok.RequiredArgsConstructor;
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
        Member member = Member.builder()
                .name(joinForWriteFormParameter.getName())
                .nickName(joinForWriteFormParameter.getNickName())
                .email(joinForWriteFormParameter.getEmail())
                .phoneNumber(joinForWriteFormParameter.getPhoneNumber())
                .department(joinForWriteFormParameter.getDepartment())
                .classNumber(joinForWriteFormParameter.getClassNumber())
                .build();

        try {
            memberService.registerMember(member);

            if (formCookie != null) {
                String formId = formCookie.getValue();
                formCookie.setPath("/");
                formCookie.setMaxAge(0);
                response.addCookie(formCookie);

                response.sendRedirect("/content/" + formId);
            } else {
                response.sendRedirect("/");
            }
        }catch (OverlapDataException e) {
            //중복 회원
            String formId = formCookie.getValue();
            formCookie.setPath("/");
            formCookie.setMaxAge(0);
            response.addCookie(formCookie);
            response.sendRedirect("/form" + formId);
        }

    }

    @GetMapping("/register/check")
    public String checkAcceptMemberPage() {
        return "/member/register/check";
    }

    @PostMapping("/register/check")
    public void checkAcceptMember(HttpServletResponse response,
                                  @RequestParam String email) throws IOException {
        try{
            memberService.checkAcceptMember(email);
            response.sendRedirect("/member/detail");
        }catch (NotAcceptMemberException e) {
            //ajax에서 구현...?
            response.sendRedirect("/member/register/check");
        }

    }


    //patch를 여러개로 분할 //TODO

    //가입승인자 정보 추가 페이지
    @GetMapping("/detail")
    public String detailPage() {
        return "/member/detail";
    }

    @PatchMapping("/detail")
    public void addMemberDetail(HttpServletResponse response,
                                @RequestBody MemberDetailFormParameter signUpFormParameter) throws IOException {
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

    //회원 상세 페이지
    @GetMapping("/{memberId}")
    public Member memberDetail(@PathVariable("memberId") Long memberId) {
        return memberService.findMemberById(memberId);
    }


    //정보 수정 페이지 TODO
    @PatchMapping("/{memberId}")
    public String updateMember(@PathVariable("memberId") Long memberId,
                               HttpServletResponse response
                              // @RequestBody
                                ) {

        return "/member/update";
    }

    //이 친구 필요할까...?
    @GetMapping("/register")
    public String registerPage() {

        return "/register";
    }

}
