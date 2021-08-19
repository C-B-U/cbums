package com.cbums.core.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Optional<Member> findByClassNumber(String classNumber);

    boolean existsByEmail(String email);

    boolean existsByClassNumber(String classNumber);

    boolean existsByNickName(String nickName);
}
