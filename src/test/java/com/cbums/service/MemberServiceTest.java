package com.cbums.service;

import com.cbums.RandomValue;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.model.Member;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    @Autowired
    HttpServletRequest request;

    @BeforeEach
    public void Session_초기화() {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        httpSession.removeAttribute("login-user");
    }

    @Test
    public void 지원자_작성() throws OverlapDataException {
        //given
        logger.info("작성자 입력");
        Member 작성자 = RandomValue.getMember();

        //when
        Member 저장된_맴버 =  memberService.registerMember(작성자);

        //then
        assertThat(작성자.getEmail()).isEqualTo(저장된_맴버.getEmail());
        assertThat(작성자.getDepartment()).isEqualTo(저장된_맴버.getDepartment());
        assertThat(작성자.getClassNumber()).isEqualTo(저장된_맴버.getClassNumber());
    }



    @Test
    public void 가입승인자_확인() throws NotAcceptMemberException, OverlapDataException, MessagingException {

        //given
        Member 작성자 = RandomValue.getMember();
        작성자.setUserRoleType(UserRoleType.MEMBER);
        memberService.registerMember(작성자);

        HttpSession httpSession = request.getSession();
        //when
        memberService.checkAcceptMember(작성자.getEmail());

        // then
        assertThat(httpSession.getAttribute("accept-email"))
                .isEqualTo(작성자.getEmail());

    }

    @Test
    public void 가입비승인자_예외처리() throws OverlapDataException {
        Member 작성자 = RandomValue.getMember();
        작성자.setUserRoleType(UserRoleType.VISITANT);
        memberService.registerMember(작성자);

        //when & then
        assertThrows(NotAcceptMemberException.class, () -> memberService.checkAcceptMember(작성자.getEmail()));
    }

    //만약 가입자가 존재하지 않다면?? TODO
    // getMembers test TODO

    @Test
    public void 가입승인자_회원가입정보_설정() throws OverlapDataException, NotAcceptMemberException, MessagingException {
        //given
        Member 작성자 = RandomValue.getMember();
        작성자.setUserRoleType(UserRoleType.MEMBER);
        memberService.registerMember(작성자);

        memberService.checkAcceptMember(작성자.getEmail());

        MemberDetailFormParameter 회원가입정보FORM = new MemberDetailFormParameter();
        회원가입정보FORM.setImage("이미지.jpg");
        //만약 이미지가 지정된 형식이 아니라면...? TODO
        회원가입정보FORM.setIntroduce("자기소개 자기소개");
        // 자기소개 길이제한이 초과된다면...? TODO
        회원가입정보FORM.setPassword("password");
        회원가입정보FORM.setPasswordCheck("password");
        // 만약 password와 체크값이 다르다면? TODO
        //when

        Long 가입자ID = memberService.setMemberDetail(회원가입정보FORM);

        //then
        HttpSession httpSession = request.getSession();
        assertThat(httpSession.getAttribute("accept-email")).isNull();
        assertThat(작성자.getEmail()).isEqualTo(memberService.findMemberById(가입자ID).getEmail());
        assertThat(회원가입정보FORM.getImage()).isEqualTo(memberService.findMemberById(가입자ID).getProfileImage());

    }

    @Test
    public void 로그아웃() throws NotLoginedException {
        //given
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("login-user", "test");

        //when
        memberService.logout();
        //then
        assertThat(httpSession.getAttribute("login-user")).isNull();

    }

}