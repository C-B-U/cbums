package com.cbums.core.project.dto;

import com.cbums.core.member.domain.MemberDetail;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.project.domain.ProjectMember;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ApplyProjectMemberResponse {

    @Autowired
    private static MemberService memberService;

    private Long projectMemberId;
    private Long memberId;
    private String picture;
    private String name;
    private String nickName;
    private Integer registerNumber;


    public static ApplyProjectMemberResponse of(ProjectMember projectMember) {

        MemberResponse md = memberService.findMember(projectMember.getMember().getMemberId());

        return new ApplyProjectMemberResponse(
                projectMember.getProjectMemberId(),
                projectMember.getMember().getMemberId(),
                projectMember.getMember().getPicture(),
                md.getName(),
                md.getNickName(),
                md.getRegisterNumber()
        );
    }

    public static List<ApplyProjectMemberResponse> listof(List<ProjectMember> projectMembers) {
        return projectMembers.stream()
                .map(ApplyProjectMemberResponse::of)
                .collect(Collectors.toList());
    }

}

