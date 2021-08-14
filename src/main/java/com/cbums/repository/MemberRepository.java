package com.cbums.repository;

import com.cbums.model.Member;
import com.cbums.type.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByClassNumber(Integer classNumber);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Member m SET" +
            " m.password = :password, m.introduce = :introduce, m.profileImage = :image " +
            "WHERE m.memberId = :memberId")
    public void updateMemberDetail(@Param("memberId") Long memberId,
                                   @Param("password") String password,
                                   @Param("introduce") String introduce,
                                   @Param("image") String image);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Member m SET" +
            " m.userRoleType = " +
            "WHERE m.memberId = :memberId")
    public void updateRoleType(@Param("memberId") Long memberId);

}
