package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class FormQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formQuestionId;

    private String content;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;

    @Column(nullable = false)
    private LocalDateTime openingDatetime;
}
