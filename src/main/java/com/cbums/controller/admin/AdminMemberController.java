package com.cbums.controller.admin;

import com.cbums.model.Member;
import com.cbums.service.MemberService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
        List<Member> memberList = memberService.findMembers();
        JsonArray jsonArr = new Gson().toJsonTree(memberList).getAsJsonArray();
        jsonObject.add("memberList", jsonArr);

        return jsonObject;
    }

    @GetMapping(value = "/{seq}")
    public JsonObject getMember(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        Member member = memberService.findMemberById(seq);
        String memberJson = new Gson().toJson(member);
        // 역직렬화 오류 TODO
        jsonObject.add("member",new Gson().toJsonTree(member).getAsJsonObject());
        return jsonObject;
    }

}
