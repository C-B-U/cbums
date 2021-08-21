package com.cbums.api.admin;

import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.dto.UpdateRoleTypeRequest;
import com.cbums.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<List<MemberResponse>> getMemberList() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable("seq") Long seq) {

        return ResponseEntity.ok(memberService.findMember(seq));

    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteMember( @PathVariable("seq") Long seq) {
        memberService.delete(seq);
        return ResponseEntity.created(URI.create("/admin/member")).build();
    }

    @PatchMapping("/{seq}/userRoleType")
    public ResponseEntity<Void> updateMemberRoleType(@PathVariable("seq") Long seq,
                                                     @Valid @RequestParam UpdateRoleTypeRequest updateRoleTypeRequest) {
        memberService.updateRole(seq, updateRoleTypeRequest);
        return ResponseEntity.created(URI.create("/admin/member")).build();
    }
}
