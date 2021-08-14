package com.cbums.service;

import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.model.Form;
import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@Transactional
@Commit
public class InputSampleDataBase {
    //Member
    final int LEFT_LIMIT = 48; // numeral '0'
    final int RIGHT_LIMIT = 122; // letter 'z'
    final int TARGET_STRING_LENGTH = 7;

    @Autowired
    MemberService memberService;
    @Autowired
    FormService formService;
    @Autowired
    FormQuestionService formQuestionService;
    @Autowired
    FormContentService formContentService;
    @Autowired
    HttpServletRequest request;


    //실행 후 test Annotation 주석처리!
    @Test
    public void 데이터_샘플_생성() throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        //맴버 생성
        //최초 생성자
        final Long FIRST_MEMBER_ID = memberService
                .registerMember(createMemberSample(UserRoleType.VISITANT))
                .getMemberId();
        httpSession.removeAttribute("form-writer-id");
        //vieited 생성
        for (int i = 0; i < 50; i++) {
            memberService.registerMember(createMemberSample(UserRoleType.VISITANT));
            httpSession.removeAttribute("form-writer-id");
        }
        //member 생성
        for (int i = 0; i < 100; i++) {
            Member member = memberService.registerMember(createMemberSample(UserRoleType.MEMBER));
            httpSession.removeAttribute("form-writer-id");
            httpSession.setAttribute("accept-email", member.getEmail());
            memberService.setMemberDetail(createMemberDetatailFormParameter());
        }
        final Long LAST_MEMBER_ID = memberService.registerMember(createMemberSample(UserRoleType.ADMIN)).getMemberId();
        httpSession.removeAttribute("form-writer-id");
        httpSession.setAttribute("accept-email", "admin");
        memberService.setMemberDetail(createMemberDetatailFormParameter());

        //form 생성
        httpSession.setAttribute("login-user", LAST_MEMBER_ID);

        final Long FIRST_FORM_ID = formService.createForm(createFormSample());
        for (int i = 0; i < 50; i++) {
            formService.createForm(createFormSample());
        }
        final Long LAST_FORM_ID = formService.createForm(createFormSample());

        //form Question 생성
        final Long FIRST_FORM_QUESTION_ID = formQuestionService.createFormQuestion(createFormQuestionSample());
        for (int i = 0; i < 100; i++) {
            formQuestionService.createFormQuestion(createFormQuestionSample());
        }
        final Long LAST_FORM_QUESTION_ID = formQuestionService.createFormQuestion(createFormQuestionSample());


        //form content 생성
        for (long i = FIRST_FORM_ID; i <= LAST_FORM_ID; i++) {
            Set<Long> formQuestions = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                formQuestions.add((long) getGeneratedInteger(FIRST_FORM_QUESTION_ID.intValue(),
                        LAST_FORM_QUESTION_ID.intValue()));
            }

            formContentService.createFormContent(i, new ArrayList<>(formQuestions));
        }


    }

    public Member createMemberSample(UserRoleType userRoleType) {

        Member member = new Member();
        member.setName(getGeneratedString());
        member.setDepartment(getGeneratedString());

        member.setClassNumber(getGeneratedInteger());
        member.setNickName(getGeneratedString());
        member.setPhoneNumber(getGeneratedInteger(10000000, 99999999).toString());
        member.setUserRoleType(userRoleType);

        if (userRoleType == UserRoleType.ADMIN) {
            member.setEmail("admin");
        } else {
            member.setEmail(getGeneratedString() + "@" + getGeneratedString());
        }

        return member;
    }

    public MemberDetailFormParameter createMemberDetatailFormParameter() {
        MemberDetailFormParameter memberDetailFormParameter
                = new MemberDetailFormParameter();
        memberDetailFormParameter.setIntroduce(getGeneratedString());
        memberDetailFormParameter.setPassword("0000");
        memberDetailFormParameter.setPasswordCheck("0000");
        memberDetailFormParameter.setImage(getGeneratedString() + ".jpg");
        return memberDetailFormParameter;
    }

    public Form createFormSample() {
        Form form = new Form();
        form.setTitle(getGeneratedString());
        form.setIntroduce(getGeneratedString());
        form.setRegisterNumber(getGeneratedInteger());
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        return form;
    }

    public FormQuestion createFormQuestionSample() {
        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setContent(getGeneratedString());
        formQuestion.setOpeningDatetime(LocalDateTime.now());
        return formQuestion;
    }

    //지원서 답변 테스트 생성 후 제작 TODO
    public void 지원서_답변_생성() {
        HttpSession httpSession = request.getSession();
        for (int i = 25; i < 50; i++) {
            httpSession.setAttribute("form-writer-id", (long) i);

        }
    }

    public Integer getGeneratedInteger() {
        Random random = new Random();
        return random.nextInt();
    }

    public Integer getGeneratedInteger(int min, int max) {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }

    public String getGeneratedString() {
        Random random = new Random();
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
