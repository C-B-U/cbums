package com.cbums.service;

import com.cbums.RandomValue;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.domain.Member;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import com.cbums.core.member.domain.UserRoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @BeforeEach
    public void Session_초기화() {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        httpSession.removeAttribute("login-user");
    }

    @Test
    public void Form_생성() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(작성자).getMemberId();

        httpSession.setAttribute("login-user", memberId);

        Form form = RandomValue.getForm();
        //when
        Long savedFormId = formService.createForm(form);
        //then

        assertThat(form.getIntroduce())
                .isEqualTo(formService
                        .findFormById(savedFormId)
                        .getIntroduce());

        assertThat(작성자.getEmail())
                .isEqualTo(formService
                        .findFormById(savedFormId)
                        .getProducer()
                        .getEmail());
    }

    @Test
    public void FORM_생성_후_객체_값_변경_시_DB저장_값도_바뀌는_지_여부() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(작성자).getMemberId();

        httpSession.setAttribute("login-user", memberId);

        Form form = RandomValue.getForm();
        //when
        Long savedFormId = formService.createForm(form);
        form.setTitle("제목 변경할래요~~");
        //then
        assertThat(form.getTitle()).isEqualTo(formService.findFormById(savedFormId).getTitle());
        logger.info("저장된 제목 값: " + formService.findFormById(savedFormId).getTitle());
    }

    @Test
    public void Form_수정() throws Exception, NotLoginedException, OverlapDataException {
        //given
        //admin계정으로 member 생성
        Member member = RandomValue.getMember();
        member.setUserRoleType(UserRoleType.ADMIN);
        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form = RandomValue.getForm();
        Long formId = formService.createForm(form);
        //수정값 생성
        Form form2 = RandomValue.getForm();
        form2.setFormId(formId);
        //when
        formId = formService.createForm(form2);
        //then
        assertThat(form2.getTitle()).isEqualTo(formService.findFormById(formId).getTitle());
        assertThat(form2.getIntroduce()).isEqualTo(formService.findFormById(formId).getIntroduce());
        assertThat(form2.getRegisterNumber()).isEqualTo(formService.findFormById(formId).getRegisterNumber());
    }

    @Test
    public void FORM_삭제() throws Exception, NotLoginedException, OverlapDataException {
        //given
        //admin계정으로 member 생성
        Member member = RandomValue.getMember();
        member.setUserRoleType(UserRoleType.ADMIN);
        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form =RandomValue.getForm();
        Long formId = formService.createForm(form);
        //when
        formService.deleteForm(formId);

        //then
        assertThrows(NullPointerException.class , () -> formService.findFormById(formId));
    }

    @Test
    public void FORM_Content_생성된_form_삭제() throws NotLoginedException, OverlapDataException {
        //given
        //admin계정으로 member 생성
        Member member = RandomValue.getMember();
        member.setUserRoleType(UserRoleType.ADMIN);

        member = memberService.registerMember(member);
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("form-writer-id");
        //처음 form 값 입력
        httpSession.setAttribute("login-user", member.getMemberId());
        Form form = RandomValue.getForm();
        Long formId = formService.createForm(form);

        Question question = RandomValue.getFormQuestion();
        formQuestionService.createFormQuestion(question);
        List<Long> formQuestionList = new ArrayList<>();
        formQuestionList.add(question.getFormQuestionId());

        FormQuestion formItem = new FormQuestion();
        formItem.setForm(form);
        formItem.setQuestion(question);
        formContentService.createFormContent(formId, formQuestionList);

        //when
        formService.deleteForm(formId);

        //then
        assertThrows(NullPointerException.class , () -> formService.findFormById(formId));
    }

}