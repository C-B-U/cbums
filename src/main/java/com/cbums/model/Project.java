package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String name;

   // @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime registerDatetime;

    private Integer maximumMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member producer;

    @Column(nullable = false)
    private boolean isProducerHidden;
    private String image;
    @Column(nullable = false)
    private Integer registerNumber;
    @Column(nullable = false)
    private boolean isRecruit;
}
