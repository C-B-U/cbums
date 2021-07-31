package com.cbums.service;

import com.cbums.model.Member;
import com.cbums.service.exception.NotAcceptMemberException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
class MemberServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    Member 작성자 = null;
    private void 작성자_생성() {
        작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
    }

    @Test
    public void 지원자_작성() {
        //given
        logger.info("작성자 입력");
        작성자_생성();

        //when
        Member 저장된_맴버 =  memberService.joinForWriteForm(작성자);

        //then
        assertEquals(작성자.getEmail(), 저장된_맴버.getEmail());
        assertEquals(작성자.getDepartment(), 저장된_맴버.getDepartment());
        assertEquals(작성자.getClassNumber(), 저장된_맴버.getClassNumber());
    }

    @Test
    public void 가입비승인자_예외처리() {
        //given
        logger.info("작성자 입력");
        작성자_생성();
        memberService.joinForWriteForm(작성자);
        boolean 통과;
        //when
        try {
            memberService.checkAcceptMember(작성자.getEmail());
            통과 = true;
        }catch (NotAcceptMemberException e) {
            통과 = false;
        }

        assertFalse(통과);
    }

    //만약 가입자가 존재하지 않다면?? TODO
    // getMembers test TODO

}