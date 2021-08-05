package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.Member;
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
class FormServiceTest {

    @Autowired
    FormService formService;
    @Autowired
    MemberService memberService;
    @Autowired
    HttpServletRequest request;

    @Test
    public void Form_생성(){
        //given
        HttpSession httpSession = request.getSession();
        Member 작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
        Long memberId = memberService.joinForWriteForm(작성자).getMemberId();

        httpSession.setAttribute("login-user",memberId);

        Form form = new Form();
        form.setIntroduce("내애요용");
        form.setTitle("제에모오오옥");
        form.setRegisterNumber(1);
        form.setOpenDateTime(LocalDateTime.now());
        form.setCloseDateTime(LocalDateTime.now());
        //when

        Long savedFormId = formService.createForm(form);
        //then

        assertEquals(form.getIntroduce(),
                formService.findFormById(savedFormId).getIntroduce());
        assertEquals(작성자.getEmail(),
                formService.findFormById(savedFormId).getProducer().getEmail());
        httpSession.removeAttribute("login-user");

    }

}