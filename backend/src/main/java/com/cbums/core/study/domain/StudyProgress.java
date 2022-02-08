package com.cbums.core.study.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyProgressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",nullable = false)
    private Study study;

    @Column(nullable = false)
    private LocalDate progressDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_member_id",nullable = false)
    private StudyMember producer;

    private String content;
    private String homework;
    private String image;
}
