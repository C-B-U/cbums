package com.cbums.core.project.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.project.domain.Project;
import com.cbums.core.project.domain.ProjectRepository;
import com.cbums.core.project.dto.ProjectRequest;
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
    private ProjectRequest projectRequest;

    @BeforeEach
    void setUp() {

        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        projectRequest = ProjectRequest.builder()
                .icon("icon.jpg")
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
        Long resultId = projectService.createProject(user, projectRequest);
        Project result = projectRepository.getById(resultId);

        //then
        assertThat(result.getIcon()).isEqualTo(projectRequest.getIcon());
        assertThat(result.getMaximumMember()).isEqualTo(projectRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(projectRequest.getName());
        assertThat(result.getProducerHidden()).isEqualTo(projectRequest.isProducerHidden());
    }

    @DisplayName("project 수정")
    @Test
    public void updateProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, projectRequest);

        projectRequest.setIcon("새로운 아이콘");
        projectRequest.setMaximumMember(2);
        projectRequest.setProducerHidden(true);
        //when
        projectService.updateProject(user, sampleId, projectRequest);
        Project result = projectRepository.getById(sampleId);
        //then
        assertThat(result.getIcon()).isEqualTo(projectRequest.getIcon());
        assertThat(result.getMaximumMember()).isEqualTo(projectRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(projectRequest.getName());
        assertThat(result.getProducerHidden()).isEqualTo(projectRequest.isProducerHidden());
    }

    @DisplayName("타인이 임의로 수정하는 것 차단")
    @Test
    public void blockUpdate() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, projectRequest);

        Member newMember = Member.builder()
                .name("새로운 사용자")
                .email("new@test.com")
                .picture("kkkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        SessionUser newUser = new SessionUser(memberRepository.save(newMember));

        projectRequest.setIcon("새로운 아이콘");
        projectRequest.setMaximumMember(2);
        projectRequest.setProducerHidden(true);

        //when & then
        assertThrows(AccessException.class,
                () -> projectService.updateProject(newUser, sampleId, projectRequest));

    }

    @DisplayName("모집 마감 & 모집중 설정")
    @Test
    public void updateRecruit() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = projectService.createProject(user, projectRequest);

        //when
        projectService.updateRecruit(user,sampleId);
        Project result = projectRepository.getById(sampleId);

        //then
        assertThat(result.getRecruit()).isEqualTo(false);

        //when
       // projectService.updateRecruit(user,sampleId);
      //  result = projectRepository.getById(sampleId);

        //then
      //  assertThat(result.isRecruit()).isEqualTo(true);

    }

}