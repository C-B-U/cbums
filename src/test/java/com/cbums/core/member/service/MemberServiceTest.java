package com.cbums.core.member.service;

import com.cbums.common.exceptions.AuthException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.security.EncryptionService;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.dto.MemberAddDetailRequest;
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
    private MemberService memberService;
    @Autowired
    private EncryptionService encryptionService;

    private SignUpRequest signUpRequest;
    private MemberAddDetailRequest memberAddDetailRequest;
    private UpdateNickNameRequest updateMemberRequest;
    private UpdateRoleTypeRequest updateRoleTypeRequest;

    @BeforeEach
    void setUp() {

        signUpRequest = SignUpRequest.builder()
                .email("test@test.com")
                .name("테스트이름")
                .phoneNumber("010-0000-0000")
                .classNumber("2018314014")
                .department("IT경영학과")
                .build();

        memberAddDetailRequest = MemberAddDetailRequest.builder()
                .introduce("자기소개입니다아아아")
                .nickName("내 별명은 뭘까")
                .password("0000")
                .profileImage("kkk.jpg")
                .build();

        updateMemberRequest = UpdateNickNameRequest.builder()
                .password("1111")
                .department("산업경영")
                .nickName("별명 바꾸기")
                .profileImage("new.png")
                .introduce("새로운 자기소개입니당")
                .phoneNumber("010-2222-3333")
                .build();

        updateRoleTypeRequest = new UpdateRoleTypeRequest(UserRoleType.MEMBER);
    }

    @DisplayName("회원 등록")
    @Test
    public void registerMember() {
        //when
        Long resultId = memberService.registerMember(signUpRequest);
        Member result = memberRepository.getById(resultId);
        //then
        assertThat(result.getEmail()).isEqualTo(signUpRequest.getEmail());
        assertThat(result.getName()).isEqualTo(signUpRequest.getName());
        assertThat(result.getPhoneNumber()).isEqualTo(signUpRequest.getPhoneNumber());
        assertThat(result.getClassNumber()).isEqualTo(signUpRequest.getClassNumber());
        assertThat(result.getDepartment()).isEqualTo(signUpRequest.getDepartment());

    }

    @DisplayName("이중회원가입")
    @Test
    public void checkExistMember(){
        //given
        memberService.registerMember(signUpRequest);
        //when & then
        assertThrows(AuthException.class,
                () ->memberService.registerMember(signUpRequest));

    }

    @DisplayName("미승인자 가입신청")
    @Test
    public void checkAdmission() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        Member sample = memberRepository.getById(sampleId);
        //when & then
        assertThrows(AuthException.class,
                () ->memberService.checkAdmission(sample.getEmail()));
        assertThrows(AuthException.class,
                () -> memberService.addDetails(sampleId,memberAddDetailRequest));
    }

    @DisplayName("승인자 추가정보 설정")
    @Test
    public void addDetails() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        Member sample = memberRepository.getById(sampleId);
        sample.setUserRoleType(UserRoleType.MEMBER);
        memberRepository.save(sample);
        //when
        memberService.addDetails(sampleId, memberAddDetailRequest);
        Member result =  memberRepository.getById(sampleId);
        //then
        assertThat(result.getEmail()).isEqualTo(signUpRequest.getEmail());
        assertThat(result.getName()).isEqualTo(signUpRequest.getName());
        assertThat(result.getPhoneNumber()).isEqualTo(signUpRequest.getPhoneNumber());
        assertThat(result.getClassNumber()).isEqualTo(signUpRequest.getClassNumber());
        assertThat(result.getDepartment()).isEqualTo(signUpRequest.getDepartment());

        assertThat(true)
                .isEqualTo(encryptionService.matches(memberAddDetailRequest.getPassword(), result.getPassword()));
        assertThat(result.getNickName()).isEqualTo(memberAddDetailRequest.getNickName());
        assertThat(result.getProfileImage()).isEqualTo(memberAddDetailRequest.getProfileImage());
        assertThat(result.getIntroduce()).isEqualTo(memberAddDetailRequest.getIntroduce());
    }

    @DisplayName("사용자 정보 변경")
    @Test
    public void updateMember() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        Member sample = memberRepository.getById(sampleId);
        sample.setUserRoleType(UserRoleType.MEMBER);
        memberRepository.save(sample);

        //when
        memberService.updateMember(sample.getEmail(), updateMemberRequest);
        Member result = memberRepository.getById(sampleId);
        //then
        assertThat(true)
                .isEqualTo(encryptionService.matches(updateMemberRequest.getPassword(), result.getPassword()));
        assertThat(result.getNickName()).isEqualTo(updateMemberRequest.getNickName());
        assertThat(result.getProfileImage()).isEqualTo(updateMemberRequest.getProfileImage());
        assertThat(result.getIntroduce()).isEqualTo(updateMemberRequest.getIntroduce());
        assertThat(result.getPhoneNumber()).isEqualTo(updateMemberRequest.getPhoneNumber());
        assertThat(result.getDepartment()).isEqualTo(updateMemberRequest.getDepartment());


    }
    //find TODO

    @DisplayName("사용자 탈퇴")
    @Test
    public void resign() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        //when
        memberService.resign(sampleId);
        Member result = memberRepository.getById(sampleId);
        //then
        assertThat(result.getResign()).isEqualTo(true);
    }

    @DisplayName("사용자 데이터 삭제")
    @Test
    public void delete() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        //when
        memberService.delete(sampleId);
        //then
        assertThrows(EntityNotFoundException.class,
                () -> memberService.findMember(sampleId));
    }

    @DisplayName("사용자 권한 변경")
    @Test
    public void updateRole() {
        //given
        Long sampleId = memberService.registerMember(signUpRequest);
        //when
        memberService.updateRole(sampleId, updateRoleTypeRequest);
        Member result = memberRepository.getById(sampleId);
        //then
        assertThat(result.getUserRoleType()).isEqualTo(updateRoleTypeRequest.getUserRoleType());
    }

}