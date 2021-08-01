package com.cbums.controller.admin;

import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.google.gson.*;
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
    public JsonObject getMemberList() {
        JsonObject jsonObject = new JsonObject();
        List<Member> memberList = memberService.findMemberList();
        JsonArray jsonArr = new Gson().toJsonTree(memberList).getAsJsonArray();
        jsonObject.add("memberList", jsonArr);

        return jsonObject;
    }

    @GetMapping(value = "/{seq}")
    public JsonObject getMember(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        Member member = memberService.findMemberById(seq);
        JsonParser jsonParser = new JsonParser();

        jsonObject.add("member",jsonParser.parse(member.toString()).getAsJsonObject());
        return jsonObject;
    }

}
