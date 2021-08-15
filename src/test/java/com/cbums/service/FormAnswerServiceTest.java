package com.cbums.service;

import com.cbums.RandomValue;
import com.cbums.model.Form;
import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.repository.FormAnswerRepository;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class FormAnswerServiceTest {

    @Autowired
    FormAnswerService formAnswerService;
    @Autowired
    MemberService memberService;
    @Autowired
    FormContentService formContentService;
    @Autowired
    FormQuestionService formQuestionService;
    @Autowired
    FormService formService;
    @Autowired
    FormAnswerRepository formAnswerRepository;
    @Autowired
    HttpServletRequest request;

    @Test
    public void 답변_생성() throws NotLoginedException, OverlapDataException {

        //given
        HttpSession httpSession = request.getSession();

        Member 작성자 = RandomValue.getMember();

        Member 저장된_맴버 = memberService.registerMember(작성자);
        httpSession.removeAttribute("form-writer-id");
        Member FORM_작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");

        Form 지원서 = RandomValue.getForm();
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = RandomValue.getMember();
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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

        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");
        httpSession.removeAttribute("login-user");
        httpSession.setAttribute("form-writer-id", 저장된_맴버.getMemberId());
        //when
        List<Long> formAnswerIdList = formAnswerService.createFormAnswer(answer);

        //then
        String answer1[] = {"답변1", "답변2", "답변3"};
        String answer2[] = {formAnswerRepository.getById(formAnswerIdList.get(0)).getContent(),
                formAnswerRepository.getById(formAnswerIdList.get(1)).getContent(),
                formAnswerRepository.getById(formAnswerIdList.get(2)).getContent()};
        Arrays.sort(answer1);
        Arrays.sort(answer2);
        Assertions.assertThat(answer1).isEqualTo(answer2);
        Assertions.assertThat(QUESTION_작성자.getEmail())
                .isEqualTo(formAnswerRepository
                        .getById(formAnswerIdList.get(0))
                        .getFormContent()
                        .getFormQuestion()
                        .getProducer()
                        .getEmail());

        Assertions.assertThat(지원서.getTitle())
                .isEqualTo(formAnswerRepository
                        .getById(formAnswerIdList.get(1))
                        .getFormContent()
                        .getForm()
                        .getTitle());

        Assertions.assertThat(작성자.getNickName())
                .isEqualTo(formAnswerRepository
                        .getById(formAnswerIdList.get(1))
                        .getMember()
                        .getNickName());
    }

    @Test
    public void 지원서_별_문항_보기() throws NotLoginedException, OverlapDataException {

        //given
        HttpSession httpSession = request.getSession();

        Member 작성자 = RandomValue.getMember();

        Member 저장된_맴버 = memberService.registerMember(작성자);
        httpSession.removeAttribute("form-writer-id");

        Member FORM_작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();

        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
        Form 지원서 = RandomValue.getForm();
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = RandomValue.getMember();
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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

        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");

        httpSession.removeAttribute("login-user");

        httpSession.setAttribute("form-writer-id", 저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);
        httpSession.setAttribute("form-writer-id", 저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);
        httpSession.setAttribute("form-writer-id", 저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);

        //then
        Assertions.assertThat(9)
                .isEqualTo(formAnswerRepository.findFormAnswerListByFormId(지원서ID).size());
    }

    @Test
    public void 지원서_종류와_작성자_지정_후_문항보기() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();

        Member FORM_작성자 = RandomValue.getMember();
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
        Form 지원서 = RandomValue.getForm();
        Long 지원서ID = formService.createForm(지원서);
        httpSession.removeAttribute("login-user");

        Member QUESTION_작성자 = RandomValue.getMember();
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");

        FormQuestion 질문1 = RandomValue.getFormQuestion();
        Long 질문ID1 = formQuestionService.createFormQuestion(질문1);

        FormQuestion 질문2 = RandomValue.getFormQuestion();
        Long 질문ID2 = formQuestionService.createFormQuestion(질문2);

        FormQuestion 질문3 =  RandomValue.getFormQuestion();
        Long 질문ID3 = formQuestionService.createFormQuestion(질문3);

        List<Long> 질문리스트 = new ArrayList<>();
        질문리스트.add(질문ID1);
        질문리스트.add(질문ID2);
        질문리스트.add(질문ID3);

        List<Long> 지원서내용ID = formContentService
                .createFormContent(지원서ID, 질문리스트);

        httpSession.removeAttribute("login-user");

        Member 작성자 = RandomValue.getMember();
        Member 저장된_맴버 = memberService.registerMember(작성자);
        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");

        formAnswerService.createFormAnswer(answer);

        Member 작성자2 = RandomValue.getMember();
        Member 저장된_맴버2 = memberService.registerMember(작성자2);
        Map<Long, String> answer2 = new HashMap<>();
        answer2.put(지원서내용ID.get(0), "답변1");
        answer2.put(지원서내용ID.get(1), "답변2");
        answer2.put(지원서내용ID.get(2), "답변3");

        formAnswerService.createFormAnswer(answer2);

        //then

        Assertions.assertThat(formAnswerRepository
                        .findFormAnswerByFormIdAndMemberId(지원서ID, 작성자2.getMemberId())
                        .size())
                .isEqualTo(3);
    }
}