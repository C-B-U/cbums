package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.Member;
import com.cbums.service.exception.NotLoginedException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FormService formService;
    @Autowired
    MemberService memberService;
    @Autowired
    HttpServletRequest request;

    @Test
    public void Form_생성() throws NotLoginedException {
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

    @Test
    public void FORM_생성_후_객체_값_변경_시_DB저장_값도_바뀌는_지_여부() {
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
        form.setTitle("제목 변경할래요~~");
        //then
        assertEquals(form.getTitle(),
                formService.findFormById(savedFormId).getTitle());
        logger.info("저장된 제목 값: "+  formService.findFormById(savedFormId).getTitle());
        httpSession.removeAttribute("login-user");


    }

}