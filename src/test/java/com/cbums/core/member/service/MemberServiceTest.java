package com.cbums.core.member.service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.*;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.UpdateIntroduceRequest;
import com.cbums.core.member.dto.UpdateNickNameRequest;
import com.cbums.core.member.dto.UpdateRoleTypeRequest;
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
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberDetailRepository memberDetailRepository;

    @Autowired
    private MemberService memberService;


    private Member member;
    private MemberAddDetailRequest memberAddDetailRequest;
    private UpdateNickNameRequest updateNickNameRequest;
    private UpdateIntroduceRequest updateIntroduceRequest;
    private UpdateRoleTypeRequest updateRoleTypeRequest;


    @BeforeEach
    void setUp() {

        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        memberAddDetailRequest = MemberAddDetailRequest.builder()
                .name("찐이름")
                .phoneNumber("010-2222-3333")
                .department("IT경영")
                .build();

        updateNickNameRequest = UpdateNickNameRequest.builder()
                .nickName("별명 바꾸기")
                .build();

        updateIntroduceRequest = UpdateIntroduceRequest.builder()
                .introduce("자기소개입니당~")
                .build();

        updateRoleTypeRequest = new UpdateRoleTypeRequest("MEMBER");
    }

    @DisplayName("승인자 추가정보 설정")
    @Test
    public void addDetails() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser sessionUser = new SessionUser(sample);
        //when
        Long resultId = memberService.addDetails(sessionUser, memberAddDetailRequest);
        MemberDetail result = memberDetailRepository.getById(resultId);
        //then
        assertThat(result.getMember()).isEqualTo(sample);
        assertThat(result.getName()).isEqualTo(memberAddDetailRequest.getName());
        assertThat(result.getPhoneNumber()).isEqualTo(memberAddDetailRequest.getPhoneNumber());
        assertThat(result.getDepartment()).isEqualTo(memberAddDetailRequest.getDepartment());
    }

    @DisplayName("사용자 닉네임 변경")
    @Test
    public void updateNickName() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser sessionUser = new SessionUser(sample);
        Long memberDetailId = memberService.addDetails(sessionUser, memberAddDetailRequest);
        //when
        memberService.updateNickName(sessionUser, updateNickNameRequest);
        MemberDetail result = memberDetailRepository.getById(memberDetailId);

        //then
        assertThat(result.getNickName())
                .isEqualTo(updateNickNameRequest.getNickName());

    }

    @DisplayName("사용자 자기소개 변경")
    @Test
    public void updateIntroduceName() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser sessionUser = new SessionUser(sample);
        Long memberDetailId = memberService.addDetails(sessionUser, memberAddDetailRequest);
        //when
        memberService.updateIntroduce(sessionUser, updateIntroduceRequest);
        MemberDetail result = memberDetailRepository.getById(memberDetailId);

        //then
        assertThat(result.getIntroduce())
                .isEqualTo(updateIntroduceRequest.getIntroduce());

    }

    @DisplayName("사용자 탈퇴")
    @Test
    public void resign() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser sessionUser = new SessionUser(sample);
        Long memberDetailId = memberService.addDetails(sessionUser, memberAddDetailRequest);
        //when
        memberService.resign(sessionUser);
        MemberDetail result = memberDetailRepository.getById(memberDetailId);
        //then
        assertThat(result.getResign()).isEqualTo(true);
    }

    @DisplayName("사용자 데이터 삭제")
    @Test
    public void delete() {
        //given
        Member sample = memberRepository.save(member);
        //when
        memberService.delete(sample.getMemberId());
        //then
        assertThrows(EntityNotFoundException.class,
                () -> memberService.findByName(sample.getName()));
    }

    @DisplayName("사용자 권한 변경")
    @Test
    public void updateRole() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser sessionUser = new SessionUser(sample);
        Long memberDetailId = memberService.addDetails(sessionUser, memberAddDetailRequest);
        //when
        memberService.updateRole(sample.getMemberId(), updateRoleTypeRequest);
        Member result = memberRepository.getById(sample.getMemberId());
        //then
        assertThat(result.getRole()).isEqualTo(UserRoleType.MEMBER);
    }

}