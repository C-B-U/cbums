package com.cbums.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String email;

    private String password;
    @Column(nullable = false)
    private String name;
    private Integer registerNumber;
    @Column(nullable = false)
    private Integer classNumber;
    @Column(nullable = false)
    private String department;
    private String profileImage;
    private String introduce;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
