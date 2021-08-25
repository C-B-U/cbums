package com.cbums.core.answer.service;

import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.answer.domain.AnswerRepository;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.dto.AnswerResponse;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.FormRepository;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.dto.FormRequest;
import com.cbums.core.form.dto.QuestionRequest;
import com.cbums.core.form.dto.QuestionResponse;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerServiceTest {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private AnswerService answerService;
    @Autowired
    private FormService formService;

    private Member member;
    private FormRequest formRequest;
    private List<QuestionRequest> questionRequests;
    private List<AnswerRequest> answerRequests;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        questionRequests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questionRequests.add(new QuestionRequest("question" + i));
        }

        formRequest = FormRequest.builder()
                .closeDateTime(LocalDateTime.now())
                .introduce("introduce")
                .openDateTime(LocalDateTime.now())
                .posterImage("posterImage")
                .title("title")
                .registerNumber(1)
                .questionRequests(questionRequests)
                .build();

        answerRequests = new ArrayList<>();

    }

    @DisplayName("ANSWER 생성")
    @Test
    public void createAnswer() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser user = new SessionUser(sample);
        Long formId = formService.createForm(user, formRequest);
        List<QuestionResponse> questionResponses =
                formService.findQuestionsByFormId(formId);

        for (int i = 0; i < questionResponses.size(); i++) {
            answerRequests.add(new AnswerRequest(
                    questionResponses.get(i).getQuestionId(),
                    "answer"+i
            ));
        }

     //when
        answerService.createAnswer(user,answerRequests);
        List<AnswerResponse> result =
                answerService.findFormAnswerByFormIdAndMemberId(member.getMemberId(),formId);

        List <String> resultContent = new ArrayList<>();
        List <String> extendContent = new ArrayList<>();
        for(AnswerResponse r :result) {
            resultContent.add(r.getContent());
        }
        for(AnswerRequest r: answerRequests) {
            extendContent.add(r.getContent());
        }
        Collections.sort(resultContent);
        Collections.sort(extendContent);

        //then
        for (int i = 0; i < resultContent.size(); i++) {
            assertThat(resultContent.get(i))
                    .isEqualTo(extendContent.get(i));
        }

    }
}