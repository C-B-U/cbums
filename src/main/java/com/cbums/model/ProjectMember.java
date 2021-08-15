package com.cbums.model;

import com.cbums.type.ProjectRoleType;
import com.cbums.type.ProjectSignUpType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMember {
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
    @Column(nullable = false)
    private ProjectRoleType projectRoleType;

    @Column(nullable = false)
    private LocalDateTime signUpDatetime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectSignUpType signUpType;

}
