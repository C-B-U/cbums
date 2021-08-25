package com.cbums.core.form.service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.FormRepository;
import com.cbums.core.form.domain.QuestionRepository;
import com.cbums.core.form.dto.FormRequest;
import com.cbums.core.form.dto.QuestionRequest;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FormServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FormRepository formRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private FormService formService;


    private Member member;
    private FormRequest formRequest;
    private QuestionRequest questionRequest;
    private List<QuestionRequest> questionRequests;

    @BeforeEach
    void setUp() {

        member = Member.builder()
                .name("테스트이름")
                .email("test@test.com")
                .picture("kkk.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();

        questionRequest = new QuestionRequest("question");

        questionRequests = new ArrayList<>();
        for(int i=0;i<10;i++) {
            questionRequests.add(new QuestionRequest("question"+i));
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
    }

    @DisplayName("FORM 생성")
    @Test
    public void createForm() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser user = new SessionUser(sample);

        //when
        Long resultId = formService.createForm(user, formRequest);
        Form result = formRepository.getById(resultId);

        //then
        assertThat(result.getProducer()).isEqualTo(sample);
        assertThat(result.getCloseDateTime()).isEqualTo(formRequest.getCloseDateTime());
        assertThat(result.getIntroduce()).isEqualTo(formRequest.getIntroduce());
        assertThat(result.getPosterImage()).isEqualTo(formRequest.getPosterImage());
        assertThat(result.getTitle()).isEqualTo(formRequest.getTitle());
        assertThat(result.getRegisterNumber()).isEqualTo(formRequest.getRegisterNumber());
        assertThat(result.getOpenDateTime()).isEqualTo(formRequest.getOpenDateTime());

        for(int i=0;i<result.getQuestionList().size();i++) {
            assertThat(result.getQuestionList().get(i).getContent())
                    .isEqualTo(formRequest.getQuestionRequests().get(i).getContent());
        }
    }

    @DisplayName("FORM 업데이트")
    @Test
    public void updateForm() {
        //given
        Member sample = memberRepository.save(member);
        SessionUser user = new SessionUser(sample);
        Long resultId = formService.createForm(user, formRequest);
        formRequest.setIntroduce("새로운 소개");
        questionRequests = new ArrayList<>();
        questionRequests.add(new QuestionRequest("new1"));
        questionRequests.add(new QuestionRequest("new2"));
        questionRequests.add(new QuestionRequest("new3"));
        questionRequests.add(new QuestionRequest("new4"));
        formRequest.setQuestionRequests(questionRequests);
        //when
        formService.updateForm(user, resultId, formRequest);
        Form result = formRepository.getById(resultId);

        //then
        assertThat(result.getProducer()).isEqualTo(sample);
        assertThat(result.getCloseDateTime()).isEqualTo(formRequest.getCloseDateTime());
        assertThat(result.getIntroduce()).isEqualTo(formRequest.getIntroduce());
        assertThat(result.getPosterImage()).isEqualTo(formRequest.getPosterImage());
        assertThat(result.getTitle()).isEqualTo(formRequest.getTitle());
        assertThat(result.getRegisterNumber()).isEqualTo(formRequest.getRegisterNumber());
        assertThat(result.getOpenDateTime()).isEqualTo(formRequest.getOpenDateTime());

        for(int i=0;i<result.getQuestionList().size();i++) {
            assertThat(result.getQuestionList().get(i).getContent())
                    .isEqualTo(formRequest.getQuestionRequests().get(i).getContent());
        }
    }

    @DisplayName("FORM 삭제")
    @Test
    public void deleteForm() {
        Member sample = memberRepository.save(member);
        SessionUser user = new SessionUser(sample);
        Long resultId = formService.createForm(user, formRequest);

        //when
        formService.deleteForm(resultId);

        //then
        assertThrows(EntityNotFoundException.class,
                () -> formService.findForm(resultId));
    }


}