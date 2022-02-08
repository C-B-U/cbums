package com.cbums.core.study.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.study.domain.Study;
import com.cbums.core.study.domain.StudyRepository;
import com.cbums.core.study.dto.CreateStudyRequest;
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
class StudyServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyService studyService;

    private Member member;
    private CreateStudyRequest createStudyRequest;

    @BeforeEach
    void setUp() {

        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        createStudyRequest = CreateStudyRequest.builder()
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
        Long resultId = studyService.createStudy(user, createStudyRequest);
        Study result = studyRepository.getById(resultId);

        //then
        assertThat(result.getMaximumMember()).isEqualTo(createStudyRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(createStudyRequest.getName());
    }

    @DisplayName("project 수정")
    @Test
    public void updateProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = studyService.createStudy(user, createStudyRequest);

        createStudyRequest.setMaximumMember(2);
        createStudyRequest.setProducerHidden(true);
        //when
        studyService.updateStudy(user, sampleId, createStudyRequest);
        Study result = studyRepository.getById(sampleId);
        //then
        assertThat(result.getMaximumMember()).isEqualTo(createStudyRequest.getMaximumMember());
        assertThat(result.getName()).isEqualTo(createStudyRequest.getName());
    }

    @DisplayName("타인이 임의로 수정하는 것 차단")
    @Test
    public void blockUpdate() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = studyService.createStudy(user, createStudyRequest);

        Member newMember = Member.builder()
                .name("새로운 사용자")
                .email("new@test.com")
                .picture("kkkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        SessionUser newUser = new SessionUser(memberRepository.save(newMember));

        createStudyRequest.setMaximumMember(2);
        createStudyRequest.setProducerHidden(true);

        //when & then
        assertThrows(AccessException.class,
                () -> studyService.updateStudy(newUser, sampleId, createStudyRequest));

    }

    @DisplayName("모집 마감 & 모집중 설정")
    @Test
    public void updateRecruit() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = studyService.createStudy(user, createStudyRequest);

        //when
        studyService.updateRecruit(user,sampleId);
        Study result = studyRepository.getById(sampleId);

        //then
        assertThat(result.getRecruit()).isEqualTo(false);

        //when
        studyService.updateRecruit(user,sampleId);
        result = studyRepository.getById(sampleId);

        //then
        assertThat(result.getRecruit()).isEqualTo(true);
    }

    @DisplayName("스터디 종료")
    @Test
    public void finishProject() {
        //given
        SessionUser user = new SessionUser(memberRepository.save(member));
        Long sampleId = studyService.createStudy(user, createStudyRequest);

        //when
        studyService.finishStudy(user, sampleId);
        Study result = studyRepository.getById(sampleId);

        //then
        assertThat(result.getFinished()).isEqualTo(true);
    }

    }