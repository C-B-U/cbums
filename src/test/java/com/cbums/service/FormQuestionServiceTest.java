package com.cbums.service;

import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FormQuestionServiceTest {

    @Autowired
    FormQuestionService formQuestionService;
    @Autowired
    MemberService memberService;
    @Autowired
    HttpServletRequest request;

    @Test
    public void FORM_질문_생성() throws NotLoginedException, OverlapDataException {
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
        httpSession.setAttribute("login-user",memberId);

        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setContent("질문 내용 입니당~");
        formQuestion.setOpeningDatetime(LocalDateTime.now());
        //when
        Long savedFormQuestion = formQuestionService.createFormQuestion(formQuestion);
        //then
        assertEquals(formQuestion.getContent(),
                formQuestionService.findFormQuestionById(savedFormQuestion).getContent());
        assertEquals(작성자.getEmail(),
                formQuestionService.findFormQuestionById(savedFormQuestion).getProducer().getEmail());
        httpSession.removeAttribute("login-user");
    }
}