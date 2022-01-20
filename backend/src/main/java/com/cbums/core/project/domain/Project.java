package com.cbums.core.project.domain;

import com.cbums.core.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime registerDatetime;

    private Integer maximumMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member producer;

    private LocalDate startDate;
    private LocalDate finishDate;

    private String rule;
    private String additionalExplain;

    @Column
    @ColumnDefault("1")
    private Boolean recruit = true;

    @Column
    @ColumnDefault("0")
    private Boolean finished = false;

    @Builder
    public Project(Long projectId, String name, LocalDateTime registerDatetime, Integer maximumMember, Member producer, LocalDate startDate, LocalDate finishDate, String rule, String additionalExplain, Boolean recruit, Boolean finished) {
        this.projectId = projectId;
        this.name = name;
        this.registerDatetime = registerDatetime;
        this.maximumMember = maximumMember;
        this.producer = producer;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.rule = rule;
        this.additionalExplain = additionalExplain;
        this.recruit = recruit;
        this.finished = finished;
    }
}
