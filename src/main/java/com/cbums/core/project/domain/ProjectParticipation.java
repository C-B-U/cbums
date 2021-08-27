package com.cbums.core.project.domain;

import com.cbums.type.AttendType;
import com.cbums.type.HomeworkSubmitType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectParticipationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_progress_id",nullable = false)
    private ProjectProgress projectProgress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_member_id",nullable = false)
    private ProjectMember member;

    @Enumerated(EnumType.STRING)
    private AttendType attendType;

    @Enumerated(EnumType.STRING)
    private HomeworkSubmitType homeworkSubmitType;
}
