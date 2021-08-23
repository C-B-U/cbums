package com.cbums.core.member.dto;

import com.cbums.core.member.domain.MemberDetail;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberResponseForAdmin {

    private Long memberId;
    private String email;
    private String role;
    private String provider;
    private String name;
    private String nickName;
    private String phoneNumber;
    private Integer registerNumber;
    private String department;
    private String picture;
    private String introduce;
    private Boolean resign;

    public static MemberResponseForAdmin of(MemberDetail member) {
        return new MemberResponseForAdmin(
                member.getMember().getMemberId(),
                member.getMember().getEmail(),
                member.getMember().getRole().getTitle(),
                member.getMember().getProvider().name(),
                member.getName(),
                member.getNickName(),
                member.getPhoneNumber(),
                member.getRegisterNumber(),
                member.getDepartment(),
                member.getMember().getPicture(),
                member.getIntroduce(),
                member.getResign());
    }

    public static List<MemberResponseForAdmin> listOf(List<MemberDetail> members) {
        return members.stream()
                .map(MemberResponseForAdmin::of)
                .collect(Collectors.toList());
    }
}
