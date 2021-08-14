package com.cbums.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Member FORM_작성자 = new Member();
        FORM_작성자.setName("홍길동");
        FORM_작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        FORM_작성자.setEmail("phjppo0918@kpu.ac.kr");
        FORM_작성자.setClassNumber(2018314014);
        FORM_작성자.setNickName("루핑투핑");
        FORM_작성자.setPhoneNumber("65745665");

        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        Form 지원서 = new Form();
        지원서.setIntroduce("내애요용");
        지원서.setTitle("제에모오오옥");
        지원서.setRegisterNumber(1);
        지원서.setOpenDateTime(LocalDateTime.now());
        지원서.setCloseDateTime(LocalDateTime.now());
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = new Member();
        QUESTION_작성자.setName("질문자");
        QUESTION_작성자.setDepartment("컴퓨터공학과");
        QUESTION_작성자.setEmail("phhjkhkjhkjhj8@kpu.ac.kr");
        QUESTION_작성자.setClassNumber(2021114014);
        QUESTION_작성자.setNickName("질문질문");
        QUESTION_작성자.setPhoneNumber("65745665");
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        FormQuestion 질문1 = new FormQuestion();
        질문1.setContent("내용1");
        질문1.setOpeningDatetime(LocalDateTime.now());
        Long 질문ID1 = formQuestionService.createFormQuestion(질문1);

        FormQuestion 질문2 = new FormQuestion();
        질문2.setContent("내용2");
        질문2.setOpeningDatetime(LocalDateTime.now());
        Long 질문ID2 = formQuestionService.createFormQuestion(질문2);

        FormQuestion 질문3 = new FormQuestion();
        질문3.setContent("내용3");
        질문3.setOpeningDatetime(LocalDateTime.now());
        Long 질문ID3 = formQuestionService.createFormQuestion(질문3);

        List<Long> 질문리스트 = new ArrayList<>();
        질문리스트.add(질문ID1);
        질문리스트.add(질문ID2);
        질문리스트.add(질문ID3);

        //when

        List<Long> 지원서내용ID = formContentService
                .createFormContent(지원서ID, 질문리스트);

        //then
        assertEquals(지원서.getIntroduce(),
                formContentRepository.getById(지원서내용ID.get(0)).getForm().getIntroduce());
        assertEquals(FORM_작성자.getEmail(),
                formContentRepository.getById(지원서내용ID.get(1)).getForm().getProducer().getEmail());


        assertEquals(QUESTION_작성자.getClassNumber(),
                formContentRepository.getById(지원서내용ID.get(0)).getFormQuestion().getProducer().getClassNumber());
        assertEquals(질문2.getContent(),
                formContentRepository.getById(지원서내용ID.get(1)).getFormQuestion().getContent());
        assertEquals(질문3.getOpeningDatetime(),
                formContentRepository.getById(지원서내용ID.get(2)).getFormQuestion().getOpeningDatetime());
    }

    @Test
    public void FORM_하나_선택해서_문항보기() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member FORM_작성자 = new Member();
        FORM_작성자.setName("홍길동");
        FORM_작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        FORM_작성자.setEmail("phjppo0918@kpu.ac.kr");
        FORM_작성자.setClassNumber(2018314014);
        FORM_작성자.setNickName("루핑투핑");
        FORM_작성자.setPhoneNumber("65745665");

        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        Form 지원서 = new Form();
        지원서.setIntroduce("내애요용");
        지원서.setTitle("제에모오오옥");
        지원서.setRegisterNumber(1);
        지원서.setOpenDateTime(LocalDateTime.now());
        지원서.setCloseDateTime(LocalDateTime.now());
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = new Member();
        QUESTION_작성자.setName("질문자");
        QUESTION_작성자.setDepartment("컴퓨터공학과");
        QUESTION_작성자.setEmail("phhjkhkjhkjhj8@kpu.ac.kr");
        QUESTION_작성자.setClassNumber(2021114014);
        QUESTION_작성자.setNickName("질문질문");
        QUESTION_작성자.setPhoneNumber("65745665");
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);

        FormQuestion 질문1 = new FormQuestion();
        질문1.setContent("내용1");
        질문1.setOpeningDatetime(LocalDateTime.now());
        Long 질문ID1 = formQuestionService.createFormQuestion(질문1);

        FormQuestion 질문2 = new FormQuestion();
        질문2.setContent("내용2");
        질문2.setOpeningDatetime(LocalDateTime.now());
        Long 질문ID2 = formQuestionService.createFormQuestion(질문2);

        FormQuestion 질문3 = new FormQuestion();
        질문3.setContent("내용3");
        질문3.setOpeningDatetime(LocalDateTime.now());
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
        assertEquals(지원서ID,
                formContents.get(0).getForm().getFormId());

        assertEquals(질문ID1,
                formContents.get(0).getFormQuestion().getFormQuestionId());
        assertEquals(질문ID2,
                formContents.get(1).getFormQuestion().getFormQuestionId());
        assertEquals(질문ID3,
                formContents.get(2).getFormQuestion().getFormQuestionId());

    }
}