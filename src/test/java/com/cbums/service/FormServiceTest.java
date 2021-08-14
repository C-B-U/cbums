package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FormServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FormService formService;
    @Autowired
    MemberService memberService;
    @Autowired
    FormQuestionService formQuestionService;
    @Autowired
    FormContentService formContentService;
    @Autowired
    HttpServletRequest request;

    @Test
    public void Form_생성() throws NotLoginedException {
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
        작성자.setPhoneNumber("65745665");
        Long memberId = memberService.registerMember(작성자).getMemberId();

        httpSession.setAttribute("login-user", memberId);

        Form form = new Form();
        form.setIntroduce("내애요용");
        form.setTitle("제에모오오옥");
        form.setRegisterNumber(1);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        //when

        Long savedFormId = formService.createForm(form);
        //then

        assertEquals(form.getIntroduce(),
                formService.findFormById(savedFormId).getIntroduce());
        assertEquals(작성자.getEmail(),
                formService.findFormById(savedFormId).getProducer().getEmail());
        httpSession.removeAttribute("login-user");

    }

    @Test
    public void FORM_생성_후_객체_값_변경_시_DB저장_값도_바뀌는_지_여부() throws NotLoginedException {
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
        작성자.setPhoneNumber("65745665");
        Long memberId = memberService.registerMember(작성자).getMemberId();

        httpSession.setAttribute("login-user", memberId);

        Form form = new Form();
        form.setIntroduce("내애요용");
        form.setTitle("제에모오오옥");
        form.setRegisterNumber(1);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        //when

        Long savedFormId = formService.createForm(form);
        form.setTitle("제목 변경할래요~~");
        //then
        assertEquals(form.getTitle(),
                formService.findFormById(savedFormId).getTitle());
        logger.info("저장된 제목 값: " + formService.findFormById(savedFormId).getTitle());
        httpSession.removeAttribute("login-user");


    }

    @Test
    public void Form_수정() throws Exception, NotLoginedException {
        //given
        //admin계정으로 member 생성
        Member member = new Member();
        member.setClassNumber(123);
        member.setDepartment("test");
        member.setEmail("add");
        member.setRegisterNumber(1);
        member.setUserRoleType(UserRoleType.ADMIN);
        member.setName("adsf");
        member.setNickName("asdf");
        member.setPhoneNumber("65745665");
        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form = new Form();
        form.setTitle("수정전");
        form.setIntroduce("수정전입니당");
        form.setRegisterNumber(4);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        Long formId = formService.createForm(form);
        //수정값 생성
        Form form2 = new Form();
        form2.setFormId(formId);
        form2.setTitle("수정후");
        form2.setIntroduce("수정후입니당");
        form2.setRegisterNumber(5);
        form2.setOpenDateTime(LocalDateTime.now());
        form2.setCloseDateTime(LocalDateTime.now());
        //when
        formId = formService.createForm(form2);
        //then
        assertEquals(form2.getTitle(), formService.findFormById(formId).getTitle());
        assertEquals(form2.getIntroduce(), formService.findFormById(formId).getIntroduce());
        assertEquals(form2.getRegisterNumber(), formService.findFormById(formId).getRegisterNumber());
    }

    @Test
    public void FORM_삭제() throws Exception, NotLoginedException {
        //given
        //admin계정으로 member 생성
        Member member = new Member();
        member.setClassNumber(123);
        member.setDepartment("test");
        member.setEmail("add");
        member.setRegisterNumber(1);
        member.setUserRoleType(UserRoleType.ADMIN);
        member.setName("adsf");
        member.setNickName("asdf");
        member.setPhoneNumber("65745665");
        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form = new Form();
        form.setTitle("수정전");
        form.setIntroduce("수정전입니당");
        form.setRegisterNumber(4);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        Long formId = formService.createForm(form);

        //when
        formService.deleteForm(formId);

        //then
        assertThrows(NullPointerException.class , () -> formService.findFormById(formId));
    }

    @Test
    public void FORM_Content_생성된_form_삭제() throws NotLoginedException {
        //given
        //admin계정으로 member 생성
        Member member = new Member();
        member.setClassNumber(123);
        member.setDepartment("test");
        member.setEmail("add");
        member.setRegisterNumber(1);
        member.setUserRoleType(UserRoleType.ADMIN);
        member.setName("adsf");
        member.setNickName("asdf");
        member.setPhoneNumber("65745665");
        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form = new Form();
        form.setTitle("수정전");
        form.setIntroduce("수정전입니당");
        form.setRegisterNumber(4);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        Long formId = formService.createForm(form);

        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setProducer(member);
        formQuestion.setContent("123");
        formQuestion.setOpeningDatetime(LocalDateTime.now());
        formQuestionService.createFormQuestion(formQuestion);
        List<Long> formQuestionList = new ArrayList<>();
        formQuestionList.add(formQuestion.getFormQuestionId());

        FormContent formContent = new FormContent();
        formContent.setForm(form);
        formContent.setFormQuestion(formQuestion);
        formContentService.createFormContent(formId, formQuestionList);

        //when
        formService.deleteForm(formId);

        //then
        assertThrows(NullPointerException.class , () -> formService.findFormById(formId));
    }

}