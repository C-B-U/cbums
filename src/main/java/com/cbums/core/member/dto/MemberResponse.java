package com.cbums.core.member.dto;

import com.cbums.core.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
