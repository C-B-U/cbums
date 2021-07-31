package com.cbums.repository;

import com.cbums.model.Member;
import com.cbums.service.MemberService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 지원서_작성_시_작성자_정보_입력() throws Exception{
        
        //given
        logger.info("작성자 입력");
        Member 작성자 = new Member();
        logger.debug("작성자 이름 입력");
        작성자.setName("홍길동");
        logger.debug("작성자 학과(복수전공 포함) 입력");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        logger.debug("작성자 이메일 입력");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        logger.debug("작성자 학번 입력");
        작성자.setClassNumber(2018314014);
        logger.debug("작성자 닉네임 입력");
        작성자.setNickName("루핑투핑");
        //when
        logger.info("작성자 데이터베이스에 저장");
        memberRepository.save(작성자);
        //then
        logger.info("저장 여부를 확인");
        assertEquals(작성자, memberRepository.findByEmail(작성자.getEmail()).get());
    }

    @Test
    public void 가입승인된_사용자_회원가입() throws Exception {

        //given

        //when

        //then
    }



}