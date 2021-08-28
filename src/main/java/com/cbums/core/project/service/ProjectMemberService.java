package com.cbums.core.project.service;

import com.cbums.common.exceptions.ActionException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.project.domain.Project;
import com.cbums.core.project.domain.ProjectMember;
import com.cbums.core.project.domain.ProjectMemberRepository;
import com.cbums.core.project.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MemberService memberService;

    @Transactional
    public Long participateProject(SessionUser sessionUser, Long projectId) {
        Member member = memberService.findByName(sessionUser.getName());
        Project project = findById(projectId);
        if(projectMemberRepository.existsByMemberAndProject(member, project)) {
            throw new ActionException(ErrorCode.ALREADY_DONE);
        }

        ProjectMember projectMember = ProjectMember.builder()
                .member(member)
                .project(project)
                .build();

        return projectMemberRepository.save(projectMember).getProjectMemberId();
    }

    private Project findById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

}
