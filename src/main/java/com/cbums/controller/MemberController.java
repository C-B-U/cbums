package com.cbums.controller;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.cbums.service.exception.CheckCodeNotEqualsException;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.OverlapDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<Void> registerMember(HttpServletResponse response,
                                               @CookieValue(value = "form-id", required = false) Cookie formCookie,
                                               @RequestBody JoinForWriteFormParameter joinForWriteFormParameter) {
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

                return ResponseEntity.created(URI.create("/content/" + formId)).build();
            } else {
                return ResponseEntity.created(URI.create("/")).build();

            }
        } catch (OverlapDataException e) {
            //중복 회원
            String formId = formCookie.getValue();
            formCookie.setPath("/");
            formCookie.setMaxAge(0);
            response.addCookie(formCookie);
            return ResponseEntity.created(URI.create("/form/" + formId)).build();
        }

    }

    @GetMapping("/register/check")
    public String checkAcceptMemberPage() {
        return "/member/register/check";
    }

    @PostMapping("/register/check")
    public ResponseEntity<Void> checkAcceptMember(@RequestParam String email) throws MessagingException {
        try {
            memberService.checkAcceptMember(email);
            return ResponseEntity.created(URI.create("/member/register/check/mail-code")).build();
        } catch (NotAcceptMemberException e) {
            //ajax에서 구현...?
            return ResponseEntity.created(URI.create("/member/register/check")).build();
        }
    }

    @GetMapping("/register/check/mail-code")
    public String checkMailCode() {
        return "/register/check/mail-code";
    }

    @PostMapping("/register/check/mail-code")
    public ResponseEntity<Void> checkMailCode(@RequestParam String emailCode) {

        try {
            memberService.checkMailCode(emailCode);
            return ResponseEntity.created(URI.create("/member/detail")).build();
        } catch (CheckCodeNotEqualsException e) {
            //일치하지 않다고 알림
            return ResponseEntity.created(URI.create("/register/check/mail-code")).build();
        }
    }

    //patch를 여러개로 분할 //TODO

    //가입승인자 정보 추가 페이지
    @GetMapping("/detail")
    public String detailPage() {
        return "/member/detail";
    }

    @PatchMapping("/detail")
    public ResponseEntity<Void> addMemberDetail(@RequestBody MemberDetailFormParameter signUpFormParameter) {
        Long memberId = memberService.setMemberDetail(signUpFormParameter);
        return ResponseEntity.created(URI.create("/member/" + memberId)).build();

    }

    // 아이디 & 비밀번호 찾기 페이지
    @GetMapping("/forgot")
    public String forgotPage() {
        return "/member/forgot";
    }

    @GetMapping("member/forgot/email")
    public String forgotEmail(@RequestParam Integer classNumber) {
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

}
