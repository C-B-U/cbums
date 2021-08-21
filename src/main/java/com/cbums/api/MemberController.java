package com.cbums.api;

import com.cbums.common.exceptions.AuthException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.dto.SignUpRequest;
import com.cbums.core.member.dto.UpdateMemberRequest;
import com.cbums.core.member.service.MemberService;
import com.cbums.common.security.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@SessionAttributes({"check-member", "hashcode"})
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final EncryptionService encryptionService;

    //getRegisterMapping에서 form id model 가져와야 TODO
    @PostMapping("/")
    public ResponseEntity<Void> registerMember(@ModelAttribute("form")Long formId,
                                               @Valid @RequestBody SignUpRequest signUpRequest) {
        memberService.registerMember(signUpRequest);

        return ResponseEntity.created(URI.create("/content/" + formId)).build();
    }


    @PostMapping("/register-check")
    public ResponseEntity<Void> checkAccept(@RequestParam String email, Model model) throws MessagingException {
        Map tempCode = memberService.checkAdmission(email);

        Long memberId = (Long) tempCode.keySet().iterator().next();
        model.addAttribute("check-member", memberId);
        model.addAttribute("hashcode", tempCode.get(memberId));
        return ResponseEntity.created(URI.create("/member/mail-check")).build();
    }

    @PostMapping("/mail-check")
    public ResponseEntity<Void> checkMail(@ModelAttribute("check-member") Long memberId,
                                          @ModelAttribute("hashcode") String hashCode,
                                          SessionStatus sessionStatus,
                                          Model model,
                                          @RequestBody String code) {

        if (!encryptionService.matches(code, hashCode)) {
            throw new AuthException(ErrorCode.NOT_MATCH_AUTHCODE);
        }

        sessionStatus.setComplete();
        model.addAttribute("check-member", memberId);
        return ResponseEntity.created(URI.create("/member/detail")).build();
    }


    @PostMapping("/detail")
    public ResponseEntity<Void> addDetail(@ModelAttribute("check-member") Long memberId,
                                          SessionStatus sessionStatus,
                                          @Valid @RequestBody MemberAddDetailRequest memberAddDetailRequest) {
        memberService.addDetails(memberId, memberAddDetailRequest);
        sessionStatus.setComplete();
        return ResponseEntity.created(URI.create("/member/detail-success")).build();

    }

    @PatchMapping("/")
    public ResponseEntity<Void> updateMember(Principal principal,
                                             @Valid @RequestBody UpdateMemberRequest updateMemberRequest) {

        Long result = memberService
                .updateMember(principal.getName(), updateMemberRequest);

        return ResponseEntity.created(URI.create("/member/" + result)).build();

    }

    @GetMapping("member/forgot/email")
    public ResponseEntity<String> forgotEmail(@RequestParam String classNumber) {
        return  ResponseEntity.ok(memberService.getBlindMemberEmail(classNumber));
    }

    @GetMapping("member/forgot/password")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) throws MessagingException {
        memberService.setTemporaryPassword(email);

        return ResponseEntity.created(URI.create("/member/set-pw-finish")).build();
    }

    //회원 상세 페이지
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable("memberId") Long memberId) {
        return  ResponseEntity.ok(memberService.findMember(memberId));
    }

}
