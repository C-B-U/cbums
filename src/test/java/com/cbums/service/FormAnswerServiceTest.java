package com.cbums.service;

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

        Member 작성자 = new Member();
        작성자.setName("작성자");
        작성자.setDepartment("작성자학과, 작성자공학과 복수전공");
        작성자.setEmail("작성자@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setPhoneNumber("01028349999");
        작성자.setNickName("작성자닉네임");

        Member 저장된_맴버 =  memberService.registerMember(작성자);
        httpSession.removeAttribute("form-writer-id");
        Member FORM_작성자 = new Member();
        FORM_작성자.setName("홍길동");
        FORM_작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        FORM_작성자.setEmail("phjppo0918@kpu.ac.kr");
        FORM_작성자.setClassNumber(2018314014);
        FORM_작성자.setNickName("루핑투핑");
        FORM_작성자.setPhoneNumber("01028349999");
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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
        QUESTION_작성자.setPhoneNumber("456776786");
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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

        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");

        httpSession.setAttribute("form-writer-id",저장된_맴버.getMemberId());
        //when
        List<Long> formAnswerIdList = formAnswerService.createFormAnswer(answer);

        //then
        String answer1[] = {"답변1", "답변2", "답변3"};
        String answer2[] = { formAnswerRepository.getById(formAnswerIdList.get(0)).getContent(),
                formAnswerRepository.getById(formAnswerIdList.get(1)).getContent(),
                formAnswerRepository.getById(formAnswerIdList.get(2)).getContent()};
        Arrays.sort(answer1);
        Arrays.sort(answer2);
        Assertions.assertThat(answer1).isEqualTo(answer2);
        assertEquals(QUESTION_작성자.getEmail(),
                formAnswerRepository.getById(formAnswerIdList.get(0)).getFormContent().getFormQuestion().getProducer().getEmail());
        assertEquals(지원서.getTitle(),
                formAnswerRepository.getById(formAnswerIdList.get(1)).getFormContent().getForm().getTitle());
        assertEquals(작성자.getNickName(),
                formAnswerRepository.getById(formAnswerIdList.get(1)).getMember().getNickName());
    }

    @Test
    public void 지원서_별_문항_보기() throws NotLoginedException, OverlapDataException {

        //given
        HttpSession httpSession = request.getSession();

        Member 작성자 = new Member();
        작성자.setName("작성자");
        작성자.setDepartment("작성자학과, 작성자공학과 복수전공");
        작성자.setEmail("작성자@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("작성자닉네임");
        작성자.setPhoneNumber("01023495969");

        Member 저장된_맴버 =  memberService.registerMember(작성자);
        httpSession.removeAttribute("form-writer-id");
        Member FORM_작성자 = new Member();
        FORM_작성자.setName("홍길동");
        FORM_작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        FORM_작성자.setEmail("phjppo0918@kpu.ac.kr");
        FORM_작성자.setClassNumber(2018314014);
        FORM_작성자.setNickName("루핑투핑");
        FORM_작성자.setPhoneNumber("12346447");
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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
        QUESTION_작성자.setPhoneNumber("17987647");
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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

        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");

        httpSession.setAttribute("form-writer-id",저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);
        httpSession.setAttribute("form-writer-id",저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);
        httpSession.setAttribute("form-writer-id",저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);

        //then
        Assertions.assertThat(9)
                .isEqualTo(formAnswerRepository.findFormAnswerListByFormId(지원서ID).size());
    }

    @Test
    public void 지원서_종류와_작성자_지정_후_문항보기() throws NotLoginedException, OverlapDataException {
        //given
        HttpSession httpSession = request.getSession();

        Member 작성자 = new Member();
        작성자.setName("작성자");
        작성자.setDepartment("작성자학과, 작성자공학과 복수전공");
        작성자.setEmail("작성자@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("작성자닉네임");
        작성자.setPhoneNumber("17987647");

        Member 저장된_맴버 =  memberService.registerMember(작성자);
        httpSession.removeAttribute("form-writer-id");
        Member FORM_작성자 = new Member();
        FORM_작성자.setName("홍길동");
        FORM_작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        FORM_작성자.setEmail("phjppo0918@kpu.ac.kr");
        FORM_작성자.setClassNumber(2018314014);
        FORM_작성자.setNickName("루핑투핑");
        FORM_작성자.setPhoneNumber("57685755");
        Long memberId = memberService.registerMember(FORM_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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
        QUESTION_작성자.setPhoneNumber("66666666");
        memberId = memberService.registerMember(QUESTION_작성자).getMemberId();
        httpSession.setAttribute("login-user", memberId);
        httpSession.removeAttribute("form-writer-id");
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

        Map<Long, String> answer = new HashMap<>();

        answer.put(지원서내용ID.get(0), "답변1");
        answer.put(지원서내용ID.get(1), "답변2");
        answer.put(지원서내용ID.get(2), "답변3");

        httpSession.setAttribute("form-writer-id",저장된_맴버.getMemberId());
        formAnswerService.createFormAnswer(answer);

        Member 작성자2 = new Member();
        작성자2.setName("작성자2");
        작성자2.setDepartment("작성자2학과, 작성자공학과 복수전공");
        작성자2.setEmail("작성자2@kpu.ac.kr");
        작성자2.setClassNumber(2018314014);
        작성자2.setNickName("작성자닉네임");
        작성자2.setPhoneNumber("68796879");
        작성자2 = memberService.registerMember(작성자2);
        Map<Long, String> answer2 = new HashMap<>();

        answer2.put(지원서내용ID.get(0), "답변1");
        answer2.put(지원서내용ID.get(1), "답변2");
        answer2.put(지원서내용ID.get(2), "답변3");

        formAnswerService.createFormAnswer(answer2);

        //then

        Assertions.assertThat(3)
                .isEqualTo(formAnswerRepository.findFormAnswerByFormIdAndMemberId(지원서ID,작성자2.getMemberId()).size());
    }
}