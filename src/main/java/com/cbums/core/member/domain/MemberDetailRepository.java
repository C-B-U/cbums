package com.cbums.core.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
    Optional<MemberDetail> findByEmail(String email);

    Optional<MemberDetail> findByClassNumber(String classNumber);

    boolean existsByEmail(String email);

    boolean existsByClassNumber(String classNumber);

    boolean existsByNickName(String nickName);
}
