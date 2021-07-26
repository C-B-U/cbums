package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
