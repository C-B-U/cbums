package com.cbums.service;

import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Random;

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
    HttpServletRequest request;


    //실행 후 test Annotation 주석처리!
   //  @Test
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
            memberService.joinForWriteForm(member);

            httpSession.setAttribute("accept-email", member.getEmail());
            SignUpFormParameter signUpFormParameter = new SignUpFormParameter();
            signUpFormParameter.setIntroduce(getGeneratedString());
            signUpFormParameter.setPassword("0000");
            signUpFormParameter.setPasswordCheck("0000");
            signUpFormParameter.setImage(getGeneratedString() + ".jpg");
            memberService.setMemberOtherInfo(signUpFormParameter);
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
        member.setPassword(new BCryptPasswordEncoder().encode("0000"));
        member.setUserRoleType(UserRoleType.ADMIN);
        memberService.joinForWriteForm(member);
    }

    public Integer getGeneratedInteger() {
        Random random = new Random();
        return random.nextInt();
    }

    public String getGeneratedString() {
        Random random = new Random();
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    //Form

    //FormQuestion

    //FormContent

    //FormAnswer
}
