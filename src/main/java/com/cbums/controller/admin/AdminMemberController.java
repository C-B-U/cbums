package com.cbums.controller.admin;

import com.cbums.model.Member;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/{seq}")
    public Member getMember(@PathVariable("seq") Long seq) {

        Member member = memberService.findMemberById(seq);
        return member;
    }

}
