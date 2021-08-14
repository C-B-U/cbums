package com.cbums.controller.admin;

import com.cbums.model.Member;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("")
    public List<Member> getMemberList() {

        List<Member> memberList = memberService.findMemberList();
        return memberList;
    }

    @GetMapping("/{seq}")
    public Member getMember(@PathVariable("seq") Long seq) {

        Member member = memberService.findMemberById(seq);
        return member;
    }

    @DeleteMapping("/{seq}")
    public void deleteMember(HttpServletResponse response, @PathVariable("seq") Long seq) throws IOException {
        memberService.deleteMember(seq);
        response.sendRedirect("/admin/member");
    }

    //member, admin 바뀌도록, updateRoletype
    @PostMapping("/{seq}/accept")
    public void acceptMember(@PathVariable("seq") Long seq) {

    }

    @PostMapping("/{seq}/reject")
    public void rejectMember(@PathVariable("seq") Long seq) {

    }
}
