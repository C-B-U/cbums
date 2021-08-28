package com.cbums.core.project.domain;

import com.cbums.core.common.BaseTimeEntity;
import com.cbums.core.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProjectMember extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private ProjectRoleType projectRoleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectSignUpType signUpType = ProjectSignUpType.UNDEFINED;

    @Builder
    public ProjectMember(Project project, Member member) {
        this.project = project;
        this.member = member;
    }
}
