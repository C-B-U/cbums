package com.cbums.core.member.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberDetailId;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String name;

    private String nickName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private Integer registerNumber;

    @Column(nullable = false, unique = true)
    private String classNumber;

    @Column(nullable = false)
    private String department;

    private String introduce;

    private Boolean resign = false;

    @Builder
    public MemberDetail(Member member, String name, String phoneNumber, String classNumber, String department) {
        this.member = member;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.classNumber = classNumber;
        this.department = department;
    }
}
