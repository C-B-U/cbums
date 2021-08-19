package com.cbums.controller.admin;

import com.cbums.core.member.domain.Member;
import com.cbums.service.MemberService;
import com.cbums.core.member.domain.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<List<Member>> getMemberList() {
        return ResponseEntity.ok(memberService.findMemberList());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<Member> getMember(@PathVariable("seq") Long seq) {

        return ResponseEntity.ok(memberService.findMemberById(seq));

    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteMember( @PathVariable("seq") Long seq) {
        memberService.deleteMember(seq);
        return ResponseEntity.created(URI.create("/admin/member")).build();
    }

    @PatchMapping("/{seq}/userRoleType")
    public ResponseEntity<Void> updateMemberRoleType(@PathVariable("seq") Long seq,
                                     @RequestParam UserRoleType userRoleType) {
        memberService.updateMemberRoleType(seq, userRoleType);
        return ResponseEntity.created(URI.create("/admin/member")).build();
    }
}
