package com.cbums.api;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.dto.SignUpRequest;
import com.cbums.core.member.dto.UpdateMemberRequest;
import com.cbums.core.member.service.MemberService;
import com.cbums.service.exception.CheckCodeNotEqualsException;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.OverlapDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<Void> registerMember(HttpServletResponse response,
                                               @CookieValue(value = "form-id", required = false) Cookie formCookie,
                                               @Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.registerMember(signUpRequest);

        String formId = formCookie.getValue();
        formCookie.setPath("/");
        formCookie.setMaxAge(0);
        response.addCookie(formCookie);

        return ResponseEntity.created(URI.create("/content/" + formId)).build();
    }

    @PostMapping("/register-check")
    public ResponseEntity<Void> checkAcceptMember(@RequestParam String email) throws MessagingException {
        memberService.checkAdmission(email);
        return ResponseEntity.created(URI.create("/member/register-check-finish")).build();
    }

    @PatchMapping("/")
    public ResponseEntity<Void> updateMember(Principal principal,
                               @Valid @RequestBody UpdateMemberRequest updateMemberRequest) {

        Long result = memberService
                .updateMember(principal.getName(), updateMemberRequest);

        return ResponseEntity.created(URI.create("/member/"+result)).build();

    }

    // 아이디 & 비밀번호 찾기 페이지
    @GetMapping("/forgot")
    public String forgotPage() {
        return "/member/forgot";
    }

    @GetMapping("member/forgot/email")
    public String forgotEmail(@RequestParam String classNumber) {
        return memberService.getBlindMemberEmail(classNumber);
    }

    @GetMapping("member/forgot/password")
    public String forgotPassword(@RequestParam String email) throws MessagingException {
        memberService.setTemporaryPassword(email);

        return "/member/forgot/password";
    }

    //회원 상세 페이지
    @GetMapping("/{memberId}")
    public Member memberDetail(@PathVariable("memberId") Long memberId) {
        return memberService.findById(memberId);
    }



}
