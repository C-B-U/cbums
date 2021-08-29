package com.cbums.core.project.dto;

import com.cbums.core.project.domain.ProjectMember;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApplyProjectMemberResponse {

    private Long projectMemberId;
    private Long memberId;
    private String picture;
    private String name;
    private String nickName;
    private Integer registerNumber;


    public static ApplyProjectMemberResponse of(ProjectMember projectMember) {

        return new ApplyProjectMemberResponse(
                projectMember.getProjectMemberId(),
                projectMember.getMember().getMemberId(),
                projectMember.getMember().getPicture(),
                projectMember.getMember().getMemberDetail().getName(),
                projectMember.getMember().getMemberDetail().getNickName(),
                projectMember.getMember().getMemberDetail().getRegisterNumber()
        );
    }

    public static List<ApplyProjectMemberResponse> listof(List<ProjectMember> projectMembers) {
        return projectMembers.stream()
                .map(ApplyProjectMemberResponse::of)
                .collect(Collectors.toList());
    }

}

