package com.cbums.repository;

import com.cbums.model.Member;
import com.cbums.type.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByMemberId(Long id);
    public Optional<Member> findByEmail(String email);
}
