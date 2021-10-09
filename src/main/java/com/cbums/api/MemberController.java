package com.cbums.api;

import com.cbums.config.auth.LoginUser;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.dto.UpdateIntroduceRequest;
import com.cbums.core.member.dto.UpdateNickNameRequest;
import com.cbums.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/detail")
    public ResponseEntity<Void> addDetail(@LoginUser SessionUser user,
                                          @Valid @RequestBody MemberAddDetailRequest memberAddDetailRequest) {
        Long result = memberService.addDetails(user, memberAddDetailRequest);
        return ResponseEntity.created(URI.create("/member/"+result)).build();
    }

    @PatchMapping("/nick-name/")
    public ResponseEntity<Void> updateNickName(@LoginUser SessionUser user,
                                             @Valid @RequestBody UpdateNickNameRequest updateNickNameRequest) {
        memberService.updateNickName(user, updateNickNameRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/introduce/")
    public ResponseEntity<Void> updateIntroduce(@LoginUser SessionUser user,
                                               @Valid @RequestBody  UpdateIntroduceRequest updateIntroduceRequest) {
        memberService.updateIntroduce(user, updateIntroduceRequest);
        return ResponseEntity.ok().build();
    }

    //회원 상세 페이지
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable("memberId") Long memberId) {
        return  ResponseEntity.ok(memberService.findMember(memberId));
    }

}
