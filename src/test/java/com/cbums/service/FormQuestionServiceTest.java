package com.cbums.service;

import com.cbums.RandomValue;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.service.FormQuestionService;
import com.cbums.core.member.domain.Member;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FormQuestionServiceTest {

    @Autowired
    FormQuestionService formQuestionService;
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
    public void FORM_질문_생성() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(작성자).getMemberId();
        httpSession.setAttribute("login-user",memberId);

        Question question = RandomValue.getFormQuestion();
        //when
        Long savedFormQuestion = formQuestionService.createFormQuestion(question);
        //then
        assertThat(question.getContent())
                .isEqualTo(formQuestionService
                        .findFormQuestionById(savedFormQuestion)
                        .getContent());

        assertThat(작성자.getEmail())
                .isEqualTo(formQuestionService
                        .findFormQuestionById(savedFormQuestion)
                        .getProducer()
                        .getEmail());
    }
}