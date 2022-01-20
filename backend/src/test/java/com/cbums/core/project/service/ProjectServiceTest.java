package com.cbums.core.project.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.project.domain.Project;
import com.cbums.core.project.domain.ProjectRepository;
import com.cbums.core.project.dto.CreateProjectRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class ProjectServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    private Member member;
    private CreateProjectRequest createProjectRequest;

    @BeforeEach
    void setUp() {

        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        createProjectRequest = CreateProjectRequest.builder()
                .maximumMember(4)
                .name("프로젝트 이름!")
                .producerHidden(false)
                .build();

    }


    @DisplayName("project 생성")
    @Test
    public void createProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        //when
        Long resultId = projectService.createProject(user, createProjectRequest);
        Project result = projectRepository.getById(resultId);

        //then
        assertThat(result.getMaximumMember()).isEqualTo(createProjectRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(createProjectRequest.getName());
    }

    @DisplayName("project 수정")
    @Test
    public void updateProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, createProjectRequest);

        createProjectRequest.setMaximumMember(2);
        createProjectRequest.setProducerHidden(true);
        //when
        projectService.updateProject(user, sampleId, createProjectRequest);
        Project result = projectRepository.getById(sampleId);
        //then
        assertThat(result.getMaximumMember()).isEqualTo(createProjectRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(createProjectRequest.getName());
    }

    @DisplayName("타인이 임의로 수정하는 것 차단")
    @Test
    public void blockUpdate() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, createProjectRequest);

        Member newMember = Member.builder()
                .name("새로운 사용자")
                .email("new@test.com")
                .picture("kkkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        SessionUser newUser = new SessionUser(memberRepository.save(newMember));

        createProjectRequest.setMaximumMember(2);
        createProjectRequest.setProducerHidden(true);

        //when & then
        assertThrows(AccessException.class,
                () -> projectService.updateProject(newUser, sampleId, createProjectRequest));

    }

    @DisplayName("모집 마감 & 모집중 설정")
    @Test
    public void updateRecruit() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, createProjectRequest);

        //when
        projectService.updateRecruit(user,sampleId);
        Project result = projectRepository.getById(sampleId);

        //then
        assertThat(result.getRecruit()).isEqualTo(false);

        //when
        projectService.updateRecruit(user,sampleId);
        result = projectRepository.getById(sampleId);

        //then
        assertThat(result.getRecruit()).isEqualTo(true);
    }

    @DisplayName("스터디 종료")
    @Test
    public void finishProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, createProjectRequest);

        //when
        projectService.finishProject(user, sampleId);
        Project result = projectRepository.getById(sampleId);

        //then
        assertThat(result.getFinished()).isEqualTo(true);
    }

    }