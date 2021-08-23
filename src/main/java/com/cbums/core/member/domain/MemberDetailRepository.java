package com.cbums.core.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {

    boolean existsByNickName(String nickName);

    @Query("SELECT md FROM MemberDetail md WHERE md.member.memberId = :memberId")
    Optional<MemberDetail> findMemberDetailsByMemberId(@Param("memberId")Long memberId);

}
