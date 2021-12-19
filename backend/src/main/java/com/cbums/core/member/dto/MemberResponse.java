package com.cbums.core.member.dto;

import com.cbums.core.member.domain.MemberDetail;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberResponse {

    private Long memberId;
    private String name;
    private String nickName;
    private Integer registerNumber;
    private String department;
    private String picture;
    private String introduce;
    private List<MemberTagResponse> tags;

    public static MemberResponse of(MemberDetail member) {
             return new MemberResponse(
                member.getMember().getMemberId(),
                member.getName(),
                member.getNickName(),
                member.getRegisterNumber(),
                member.getDepartment(),
                member.getMember().getPicture(),
                member.getIntroduce(),
                MemberTagResponse.listOf(member.getMember().memberTags));
    }

    public static List<MemberResponse> listOf(List<MemberDetail> members) {
        return members.stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }
}
