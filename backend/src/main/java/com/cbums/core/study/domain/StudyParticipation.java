package com.cbums.core.study.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyParticipationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_progress_id",nullable = false)
    private StudyProgress studyProgress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_member_id",nullable = false)
    private StudyMember member;

    @Enumerated(EnumType.STRING)
    private AttendType attendType;

    @Enumerated(EnumType.STRING)
    private HomeworkSubmitType homeworkSubmitType;
}
