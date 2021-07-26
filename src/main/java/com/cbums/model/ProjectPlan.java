package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class ProjectPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectPlanId;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;

    private LocalDate planDate;
    private String content;
    private String homework;
}
