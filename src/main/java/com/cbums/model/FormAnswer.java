package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FormAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formAnswerId;

    @ManyToOne
    @JoinColumn(name = "form_id",nullable = false)
    private Form form;

    @ManyToOne
    @JoinColumn(name = "form_question_id",nullable = false)
    private FormQuestion formQuestion;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    private String content;
}


