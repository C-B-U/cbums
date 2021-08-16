package com.cbums.core.member.dto;

import com.cbums.core.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberResponse {

    private String email;
    private String name;
    private String nickName;
    private Integer registerNumber;
    private String department;
    private String profileImage;
    private String introduce;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getEmail(),
                member.getName(),
                member.getNickName(),
                member.getRegisterNumber(),
                member.getDepartment(),
                member.getProfileImage(),
                member.getIntroduce());
    }

    public static List<MemberResponse> listOf(List<Member> members) {
        return members.stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }
}
