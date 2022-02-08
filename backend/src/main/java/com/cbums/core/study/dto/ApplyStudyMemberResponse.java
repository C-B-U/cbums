package com.cbums.core.study.dto;

import com.cbums.core.study.domain.StudyMember;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApplyStudyMemberResponse {

    private Long projectMemberId;
    private Long memberId;
    private String picture;
    private String name;
    private String nickName;
    private Integer registerNumber;


    public static ApplyStudyMemberResponse of(StudyMember studyMember) {

        return new ApplyStudyMemberResponse(
                studyMember.getStudyMemberId(),
                studyMember.getMember().getMemberId(),
                studyMember.getMember().getPicture(),
                studyMember.getMember().getMemberDetail().getName(),
                studyMember.getMember().getMemberDetail().getNickName(),
                studyMember.getMember().getMemberDetail().getRegisterNumber()
        );
    }

    public static List<ApplyStudyMemberResponse> listof(List<StudyMember> studyMembers) {
        return studyMembers.stream()
                .map(ApplyStudyMemberResponse::of)
                .collect(Collectors.toList());
    }

}

