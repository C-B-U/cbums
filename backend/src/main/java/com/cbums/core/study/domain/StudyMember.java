package com.cbums.core.study.domain;

import com.cbums.core.common.domain.BaseTimeEntity;
import com.cbums.core.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StudyMember extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id", nullable = false)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private StudyRoleType studyRoleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudySignUpType signUpType = StudySignUpType.UNDEFINED;

    @Builder
    public StudyMember(Study study, Member member) {
        this.study = study;
        this.member = member;
    }
}
