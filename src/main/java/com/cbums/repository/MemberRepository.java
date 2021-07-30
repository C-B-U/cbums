package com.cbums.repository;

import com.cbums.model.Member;
import com.cbums.type.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByMemberId(Long id);
    public Optional<Member> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Member m SET" +
            " m.password = :password, m.introduce = :introduce, m.profileImage = :image " +
            "WHERE m.memberId = :memberId")
    public void setAcceptMember(Long memberId, String password, String introduce, String image);
}
