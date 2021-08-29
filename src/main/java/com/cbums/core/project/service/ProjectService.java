package com.cbums.core.project.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.common.exceptions.ActionException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.project.domain.*;
import com.cbums.core.project.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MemberService memberService;

    @Transactional
    public Long createProject(SessionUser user, ProjectRequest projectRequest) {
        Project project = buildProject(projectRequest);
        project.setRegisterDatetime(LocalDateTime.now());
        Member producer = memberService.findByName(user.getName());
        project.setProducer(producer);
        //현재 기간이 몇 기수인지 자동으로 입력 TODO
        project.setRegisterNumber(10);

        return projectRepository.save(project).getProjectId();
    }

    private Project buildProject(ProjectRequest projectRequest) {
        return new Project().builder()
                .producerHidden(projectRequest.isProducerHidden())
                .icon(projectRequest.getIcon())
                .maximumMember(projectRequest.getMaximumMember())
                .name(projectRequest.getName())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> findAll() {
        return ProjectResponse.listOf(projectRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ProjectResponse findProject(Long projectId) {
        return ProjectResponse.of(findById(projectId));
    }

    private Project findById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    @Transactional
    public void updateRecruit(SessionUser user, Long projectId) {
        Project project = findById(projectId);

        confirmMember(user, project.getProducer());

        if(project.getRecruit()) {
            project.setRecruit(false);
        }else {
            project.setRecruit(true);
        }

    }

    @Transactional
    public void updateProject(SessionUser user, Long projectId, ProjectRequest projectRequest) {
        Project project = findById(projectId);

        confirmMember(user, project.getProducer());

        project.setName(projectRequest.getName());
        project.setMaximumMember(projectRequest.getMaximumMember());
        project.setProducerHidden(projectRequest.isProducerHidden());
        project.setIcon(projectRequest.getIcon());

    }
    public void finishProject(SessionUser user, Long projectId) {
        Project project = findById(projectId);

        confirmMember(user, project.getProducer());

        project.setFinished(true);
    }

    private void confirmMember(SessionUser user, Member member) {
        if (member.getName() != user.getName() &&
                member.getRole() != UserRoleType.ADMIN) {
                throw new AccessException(ErrorCode.USER_BAD_ACCESS);
        }
    }

    //projectMember

    @Transactional
    public Long applyProject(SessionUser user, Long projectId) {
        Member member = memberService.findByName(user.getName());
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

    @Transactional
    public void cancelApply(SessionUser user, Long projectId) {
        Member member = memberService.findByName(user.getName());
        Project project = findById(projectId);

        projectMemberRepository.deleteByMemberAndProject(member, project);
    }

    @Transactional(readOnly = true)
    public List<ApplyProjectMemberResponse> findApplyMember(SessionUser user, Long projectId) {
        Project project = findById(projectId);
        confirmMember(user, project.getProducer());
        List<ProjectMember> result = projectMemberRepository.findAllByProject(project);

        return ApplyProjectMemberResponse.listof(result);
    }

    @Transactional
    public void updateSignUpType(SessionUser user, Long projectMemberId, ProjectSignUpTypeRequest signUpTypeRequest) {
        ProjectMember projectMember = findProjectMemberById(projectMemberId);
        confirmMember(user, projectMember.getProject().getProducer());
        ProjectSignUpType signUpType = ProjectSignUpType.UNDEFINED;
        switch (signUpTypeRequest.getProjectSignUpType()) {
            case "ALLOW":
                signUpType = ProjectSignUpType.ALLOW;
                break;
            case "REFUSE":
                signUpType = ProjectSignUpType.REFUSE;
                break;
        }
        projectMember.setSignUpType(signUpType);
    }

    @Transactional
    public void updateRoleType(SessionUser user, Long projectMemberId, ProjectRoleTypeRequest roleTypeRequest) {
        ProjectMember projectMember = findProjectMemberById(projectMemberId);
        confirmMember(user, projectMember.getProject().getProducer());

        ProjectRoleType roleType = ProjectRoleType.MEMBER;
        switch (roleTypeRequest.getProjectRoleType()) {
            case "LEADER":
                roleType = ProjectRoleType.LEADER;
                break;
            case "MENTOR":
                roleType = ProjectRoleType.MENTOR;
                break;
            case "CLERK":
                roleType = ProjectRoleType.CLERK;
                break;
        }
        projectMember.setProjectRoleType(roleType);
    }

    private ProjectMember findProjectMemberById(Long projectMemberId){
        return projectMemberRepository.findById(projectMemberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }



}