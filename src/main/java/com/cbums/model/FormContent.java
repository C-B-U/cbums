package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FormContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formContentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id",nullable = false)
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_question_id",nullable = false)
    private FormQuestion formQuestion;
}
