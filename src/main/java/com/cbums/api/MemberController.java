package com.cbums.api;

import com.cbums.controller.postParameter.JoinForWriteFormParameter;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.dto.SignUpRequest;
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

    @PostMapping("/register/check")
    public ResponseEntity<Void> checkAcceptMember(@RequestParam String email) {
        memberService.checkAdmission(email);
        return ResponseEntity.created(URI.create("/member/register/check/finish")).build();
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
