package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @Column(nullable = false)
    private String title;

    private String introduce;
    @Column(nullable = false)
    private LocalDateTime openDateTime;
    @Column(nullable = false)
    private LocalDateTime closeDateTime;

    private Integer registerNumber; // 비모집 지워서인 경우 null

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;
}
