package com.cbums.core.project.domain;

import com.cbums.core.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
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

    @Column(nullable = false)
    private Boolean producerHidden;

    private String icon;

    @Column(nullable = false)
    private Integer registerNumber;

    @Column
    @ColumnDefault("1")
    private Boolean recruit = true;

    @Builder
    public Project(String name, LocalDateTime registerDatetime, Integer maximumMember, Member producer, Boolean producerHidden, String icon, Integer registerNumber) {
        this.name = name;
        this.registerDatetime = registerDatetime;
        this.maximumMember = maximumMember;
        this.producer = producer;
        this.producerHidden = producerHidden;
        this.icon = icon;
        this.registerNumber = registerNumber;
    }
}
