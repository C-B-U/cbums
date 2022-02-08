package com.cbums.core.study.domain;

import com.cbums.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    boolean existsByMemberAndStudy(Member member, Study study);

    List<StudyMember> findAllByStudy(Study study);

    void deleteByMemberAndStudy(Member member, Study study);
}