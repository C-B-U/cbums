package com.cbums.service;

import com.cbums.RandomValue;
import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.repository.FormContentRepository;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FormContentServiceTest {

    @Autowired
    FormContentService formContentService;
    @Autowired
    FormQuestionService formQuestionService;
    @Autowired
    FormService formService;
    @Autowired
    MemberService memberService;
    @Autowired
    FormContentRepository formContentRepository;

    @Autowired
    HttpServletRequest request;

    @Test
    public void FORM_내용_구성() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member FORM_작성자 = RandomValue.getMember();

        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        Form 지원서 = RandomValue.getForm();
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = RandomValue.getMember();
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        FormQuestion 질문1 = RandomValue.getFormQuestion();
        Long 질문ID1 = formQuestionService.createFormQuestion(질문1);

        FormQuestion 질문2 = RandomValue.getFormQuestion();
        Long 질문ID2 = formQuestionService.createFormQuestion(질문2);

        FormQuestion 질문3 = RandomValue.getFormQuestion();
        Long 질문ID3 = formQuestionService.createFormQuestion(질문3);

        List<Long> 질문리스트 = new ArrayList<>();
        질문리스트.add(질문ID1);
        질문리스트.add(질문ID2);
        질문리스트.add(질문ID3);

        //when

        List<Long> 지원서내용ID = formContentService
                .createFormContent(지원서ID, 질문리스트);

        //then

        assertThat(지원서.getIntroduce())
                .isEqualTo(formContentRepository
                        .getById(지원서내용ID.get(0))
                        .getForm()
                        .getIntroduce());
        assertThat(FORM_작성자.getEmail())
                .isEqualTo(formContentRepository
                        .getById(지원서내용ID.get(1))
                        .getForm()
                        .getProducer()
                        .getEmail());

        assertThat(QUESTION_작성자.getClassNumber())
                .isEqualTo(formContentRepository
                        .getById(지원서내용ID.get(0))
                        .getFormQuestion()
                        .getProducer()
                        .getClassNumber());

        assertThat(질문2.getContent())
                .isEqualTo(formContentRepository
                        .getById(지원서내용ID.get(1))
                        .getFormQuestion()
                        .getContent());

        assertThat(질문3.getOpeningDatetime())
                .isEqualTo(formContentRepository
                        .getById(지원서내용ID.get(2))
                        .getFormQuestion()
                        .getOpeningDatetime());
    }

    @Test
    public void FORM_하나_선택해서_문항보기() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member FORM_작성자 = RandomValue.getMember();

        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        Form 지원서 = RandomValue.getForm();
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = RandomValue.getMember();
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        FormQuestion 질문1 = RandomValue.getFormQuestion();
        Long 질문ID1 = formQuestionService.createFormQuestion(질문1);

        FormQuestion 질문2 = RandomValue.getFormQuestion();
        Long 질문ID2 = formQuestionService.createFormQuestion(질문2);

        FormQuestion 질문3 = RandomValue.getFormQuestion();
        Long 질문ID3 = formQuestionService.createFormQuestion(질문3);

        List<Long> 질문리스트 = new ArrayList<>();
        질문리스트.add(질문ID1);
        질문리스트.add(질문ID2);
        질문리스트.add(질문ID3);

        List<Long> 지원서내용ID = formContentService
                .createFormContent(지원서ID, 질문리스트);

        //when
        List<FormContent> formContents = formContentService.findFormContentListByFormId(지원서ID);

        //then
        assertThat(지원서ID).isEqualTo(formContents.get(0).getForm().getFormId());
        assertThat(질문ID1).isEqualTo(formContents.get(0).getFormQuestion().getFormQuestionId());
        assertThat(질문ID2).isEqualTo(formContents.get(1).getFormQuestion().getFormQuestionId());
        assertThat(질문ID3).isEqualTo(formContents.get(2).getFormQuestion().getFormQuestionId());
    }
}