package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ProjectProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectProgressId;

    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false)
    private Project project;

    @Column(nullable = false)
    private LocalDate progressDate;

    @ManyToOne
    @JoinColumn(name = "project_member_id",nullable = false)
    private ProjectMember producer;

    private String content;
    private String homework;
    private String image;
}
