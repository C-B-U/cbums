package com.cbums.api;

import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.study.domain.Study;
import com.cbums.core.study.domain.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/sample")
@RequiredArgsConstructor
public class DBSampleController {

    private final MemberRepository memberRepository;
    private final StudyRepository studyRepository;

    @GetMapping("")
    public String hello() {
        return "샘플 데이터입니다";
    }

    @GetMapping("/member")
    public ResponseEntity<Void> insertMember() {
        Member member0 = Member.builder()
                .name("홍길동")
                .email("Test123@naver.com")
                .picture("abc.jpg")
                .role(UserRoleType.GUEST)
                .provider(AuthProvider.KAKAO)
                .build();
        Member member1 = Member.builder()
                .name("아이유")
                .email("Te3@naver.com")
                .picture("addbc.jpg")
                .role(UserRoleType.MEMBER)
                .provider(AuthProvider.NAVER)
                .build();
        Member member2 = Member.builder()
                .name("유재석")
                .email("asdf123@naver.com")
                .picture("abffc.jpg")
                .role(UserRoleType.MEMBER)
                .provider(AuthProvider.GOOGLE)
                .build();
        Member member3 = Member.builder()
                .name("관리자")
                .email("admin@naver.com")
                .picture("admin.jpg")
                .role(UserRoleType.ADMIN)
                .provider(AuthProvider.KAKAO)
                .build();
        memberRepository.save(member0);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/project")
    public ResponseEntity<Void> insertProject() {

        Member projectProducer1 = Member.builder()
                .name("스터디 제작자1")
                .email("admin@naver.com")
                .picture("admin.jpg")
                .role(UserRoleType.ADMIN)
                .provider(AuthProvider.KAKAO)
                .build();
        Member projectProducer2 = Member.builder()
                .name("스터디 제작자2")
                .email("admin@naver.com")
                .picture("admin.jpg")
                .role(UserRoleType.ADMIN)
                .provider(AuthProvider.KAKAO)
                .build();
        Member projectProducer3 = Member.builder()
                .name("스터디 제작자3")
                .email("admin@naver.com")
                .picture("admin.jpg")
                .role(UserRoleType.ADMIN)
                .provider(AuthProvider.KAKAO)
                .build();
        memberRepository.save(projectProducer1);
        memberRepository.save(projectProducer2);
        memberRepository.save(projectProducer3);

        Study study0 = Study.builder()
                .name("자바 스터디")
                .registerDatetime(LocalDateTime.now())
                .maximumMember(5)
                .producer(projectProducer1)
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .rule("asdf")
                .additionalExplain("adsfasdf")
                .build();

        Study study1 = Study.builder()
                .name("파이썬 스터디")
                .registerDatetime(LocalDateTime.now())
                .maximumMember(6)
                .producer(projectProducer2)
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .rule("asdf")
                .additionalExplain("adsfasdf")
                .build();

        Study study2 = Study.builder()
                .name("알고리즘 스터디")
                .registerDatetime(LocalDateTime.now())
                .maximumMember(6)
                .producer(projectProducer3)
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .rule("asdf")
                .additionalExplain("adsfasdf")
                .build();
        studyRepository.save(study0);
        studyRepository.save(study1);
        studyRepository.save(study2);

        return ResponseEntity.ok().build();
    }
}
