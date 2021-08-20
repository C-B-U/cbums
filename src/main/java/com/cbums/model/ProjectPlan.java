package com.cbums.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectPlanId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;

    private LocalDate planDate;
    private String content;
    private String homework;
}
