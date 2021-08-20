package com.cbums.repository;

import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
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
    MemberRepository memberRepository;

    Member 작성자 = null;
    private void 작성자_생성() {
        작성자 = new Member();
        작성자.setName("홍길동");
        작성자.setDepartment("IT경영학과, 컴퓨터공학과 복수전공");
        작성자.setEmail("phjppo0918@kpu.ac.kr");
        작성자.setClassNumber(2018314014);
        작성자.setNickName("루핑투핑");
        작성자.setPhoneNumber("01028349999");
    }
    @Test
    public void 지원서_작성_시_작성자_정보_입력() {


        //given
        logger.info("작성자 입력");
        작성자_생성();
        //when
        logger.info("작성자 데이터베이스에 저장");
        memberRepository.save(작성자);
        //then
        logger.info("저장 여부를 확인");
        assertEquals(작성자, memberRepository.findByEmail(작성자.getEmail()).get());
    }

    @Test
    public void 가입승인된_사용자_회원가입() {

        //given
        logger.info("작성자 입력");
        작성자_생성();
        logger.info("작성자 데이터베이스에 저장");
        memberRepository.save(작성자);

        logger.info("세부사항입력양식 생성");
        MemberDetailFormParameter 세부사항입력양식 = new MemberDetailFormParameter();
        세부사항입력양식.setPassword("0000");
        세부사항입력양식.setImage("이미지.jpg");
        세부사항입력양식.setIntroduce("안녕하세요! 저는 아아아아어어어어오오오오우우우우");
        //when
        Member 세부사항_추가_사용자 = memberRepository.findByEmail(작성자.getEmail()).get();
        logger.info("세부사항 업데이트");


        memberRepository.updateMemberDetail(세부사항_추가_사용자.getMemberId(),
                세부사항입력양식.getPassword(),
                세부사항입력양식.getIntroduce(),
                세부사항입력양식.getImage());
        logger.info("비교를 위한 세부사항 추가 사용자 객체 수정");
        세부사항_추가_사용자.setPassword(세부사항입력양식.getPassword());
        세부사항_추가_사용자.setIntroduce(세부사항입력양식.getIntroduce());
        세부사항_추가_사용자.setProfileImage(세부사항입력양식.getImage());
        //then
        logger.info("저장 여부를 확인");
        assertEquals(작성자.getEmail(), memberRepository.findByEmail(작성자.getEmail()).get().getEmail());
        assertEquals(작성자.getPassword(), memberRepository.findByEmail(작성자.getEmail()).get().getPassword());
        assertEquals(작성자.getNickName(), memberRepository.findByEmail(작성자.getEmail()).get().getNickName());
    }



}