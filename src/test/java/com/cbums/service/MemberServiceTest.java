package com.cbums.service;

import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    @Autowired
    HttpServletRequest request;

    Member 작성자 = null;

    @BeforeEach
    private void 테스트_시작_전_작성자_초기화 (){
        작성자 = null;
    }
    @DisplayName("작성자 생성")
    private void 작성자_생성() {
        작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
        작성자.setPhoneNumber("65745665");
    }

    @Test
    public void 지원자_작성() {
        //given
        logger.info("작성자 입력");
        작성자_생성();

        //when
        Member 저장된_맴버 =  memberService.joinForWriteForm(작성자);

        //then
        assertEquals(작성자.getEmail(), 저장된_맴버.getEmail());
        assertEquals(작성자.getDepartment(), 저장된_맴버.getDepartment());
        assertEquals(작성자.getClassNumber(), 저장된_맴버.getClassNumber());
    }



    @Test
    public void 가입승인자_확인() throws NotAcceptMemberException{

        //given
        작성자_생성();
        작성자.setUserRoleType(UserRoleType.MEMBER);
        memberService.joinForWriteForm(작성자);

        HttpSession httpSession = request.getSession();
        //when
        memberService.checkAcceptMember(작성자.getEmail());

        // then
        assertEquals(
                httpSession.getAttribute("accept-email"),
                작성자.getEmail());

    }

    @Test
    public void 가입비승인자_예외처리() {
        작성자_생성();
        memberService.joinForWriteForm(작성자);
        //when & then
        assertThrows(NotAcceptMemberException.class, () ->
                memberService.checkAcceptMember(작성자.getEmail()));
    }

    //만약 가입자가 존재하지 않다면?? TODO
    // getMembers test TODO

    @Test
    public void 가입승인자_회원가입정보_설정() {
        //given
        작성자_생성();
        작성자.setUserRoleType(UserRoleType.MEMBER);
        memberService.joinForWriteForm(작성자);
        try{
            memberService.checkAcceptMember(작성자.getEmail());
        }catch (NotAcceptMemberException e){
            assertTrue(false);
        }

        SignUpFormParameter 회원가입정보FORM = new SignUpFormParameter();
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
        assertNull(httpSession.getAttribute("accept-email"));
        assertEquals(작성자.getEmail(),
                memberService.findMemberById(가입자ID).getEmail());
        assertEquals(회원가입정보FORM.getImage(),
                memberService.findMemberById(가입자ID).getProfileImage());

    }

    @Test
    public void 로그아웃() throws NotLoginedException {
        //given
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("login-user", "test");

        //when
        memberService.logout();
        //then
        assertNull(httpSession.getAttribute("login-user"));

    }

}