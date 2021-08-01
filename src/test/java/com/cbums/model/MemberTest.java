package com.cbums.model;

import com.cbums.type.UserRoleType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MemberTest {

    @Test
    public void JSON_변환() {
        Member member = new Member();
        member.setEmail("aaa");
        member.setMemberId(1L);
        member.setNickName("아아");
        member.setPassword("asdf");
        member.setIntroduce("ssss");
        member.setUserRoleType(UserRoleType.ADMIN);

        System.out.println(member.toString());

        assertTrue(true);
    }

}