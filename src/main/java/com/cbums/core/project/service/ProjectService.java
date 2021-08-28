package com.cbums.core.project.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.project.domain.Project;
import com.cbums.core.project.domain.ProjectRepository;
import com.cbums.core.project.dto.ProjectRequest;
import com.cbums.core.project.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
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

        if(project.isRecruit()) {
            project.setRecruit(false);
        }else {
            project.setRecruit(true);
        }
        projectRepository.save(project);
    }

    @Transactional
    public void updateProject(SessionUser user, Long projectId, ProjectRequest projectRequest) {
        Project project = findById(projectId);

        confirmMember(user, project.getProducer());

        project.setName(projectRequest.getName());
        project.setMaximumMember(projectRequest.getMaximumMember());
        project.setProducerHidden(projectRequest.isProducerHidden());
        project.setIcon(projectRequest.getIcon());

        projectRepository.save(project);
    }

    private void confirmMember(SessionUser user, Member member) {
        if (member.getName() != user.getName()) {
            throw new AccessException(ErrorCode.USER_BAD_ACCESS);
        }
    }


}