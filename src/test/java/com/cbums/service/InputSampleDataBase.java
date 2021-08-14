package com.cbums.service;

import com.cbums.controller.postParameter.SignUpFormParameter;
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
   // @Test
    public void 맴버생성() {
        HttpSession httpSession = request.getSession();
        //VISITED
        for (int i = 0; i < 50; i++) {
            Member member = new Member();
            member.setName(getGeneratedString());
            member.setDepartment(getGeneratedString());
            member.setEmail(getGeneratedString() + "@" + getGeneratedString());
            member.setClassNumber(getGeneratedInteger());
            member.setNickName(getGeneratedString());
            member.setPhoneNumber("65745665");
            memberService.joinForWriteForm(member);

            httpSession.removeAttribute("form-writer-id");
        }
        //MEMBER
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setName(getGeneratedString());
            member.setDepartment(getGeneratedString());
            member.setEmail(getGeneratedString() + "@" + getGeneratedString());
            member.setClassNumber(getGeneratedInteger());
            member.setNickName(getGeneratedString());
            member.setUserRoleType(UserRoleType.MEMBER);
            member.setPhoneNumber("65745665");
            memberService.joinForWriteForm(member);

            httpSession.setAttribute("accept-email", member.getEmail());
            SignUpFormParameter signUpFormParameter = new SignUpFormParameter();
            signUpFormParameter.setIntroduce(getGeneratedString());
            signUpFormParameter.setPassword("0000");
            signUpFormParameter.setPasswordCheck("0000");
            signUpFormParameter.setImage(getGeneratedString() + ".jpg");
            memberService.setMemberDetail(signUpFormParameter);
        }

        //ADMIN
        Member member = new Member();
        member.setName("admin");
        member.setDepartment("admin");
        member.setEmail("admin");
        member.setNickName("admin");
        member.setClassNumber(1);
        member.setRegisterNumber(1);
        member.setIntroduce("admin");
        member.setProfileImage("admin.png");
        member.setPhoneNumber("65745665");
        member.setPassword(new BCryptPasswordEncoder().encode("0000"));
        member.setUserRoleType(UserRoleType.ADMIN);
        memberService.joinForWriteForm(member);
    }

    //실행 후 test Annotation 주석처리!
    //@Test
    public void 지원서_생성() throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("login-user", 151l);

        for (int i = 0; i < 50; i++) {
            Form form = new Form();
            form.setTitle(getGeneratedString());
            form.setIntroduce(getGeneratedString());
            form.setRegisterNumber(getGeneratedInteger());
            form.setOpenDateTime(LocalDateTime.now());
            form.setCloseDateTime(LocalDateTime.now());
            formService.createForm(form);
        }
    }

    //실행 후 test Annotation 주석처리!
    //@Test
    public void 문항_생성() throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("login-user", 151l);

        for (int i = 0; i < 150; i++) {
            FormQuestion formQuestion = new FormQuestion();
            formQuestion.setContent(getGeneratedString());
            formQuestion.setOpeningDatetime(LocalDateTime.now());
            formQuestionService.createFormQuestion(formQuestion);
        }
    }


    //실행 후 test Annotation 주석처리!
    //@Test
    public void 지원서_내용_제작() {
        for(int i=6;i<=55;i++) {
            Set<Long> formQuestions = new HashSet<>();
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));
            formQuestions.add((long)getGeneratedInteger(1,150));

            formContentService.createFormContent((long)i,new ArrayList<>(formQuestions));
        }
    }

    //지원서 답변 테스트 생성 후 제작 TODO
    public void 지원서_답변_생성() {
        HttpSession httpSession = request.getSession();
        for(int i=25;i<50;i++) {
            httpSession.setAttribute("form-writer-id", (long)i);

        }
    }

    public Integer getGeneratedInteger() {
        Random random = new Random();
        return random.nextInt();
    }
    public Integer getGeneratedInteger(int min, int max) {
        Random random = new Random();

        return random.nextInt(max-min) + min;
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
