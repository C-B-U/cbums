package com.cbums.core.member.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phoneNumber;

    private Integer registerNumber;

    @Column(nullable = false)
    private String classNumber;

    @Column(nullable = false)
    private String department;

    private String profileImage;

    private String introduce;

    @Enumerated(EnumType.STRING)
    private UserRoleType userRoleType = UserRoleType.VISITANT;

    @Builder
    public Member(String email, String name, String nickName, String phoneNumber, String classNumber, String department) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.classNumber = classNumber;
        this.department = department;
    }
}
