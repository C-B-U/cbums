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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_content_id",nullable = false)
    private FormContent formContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    private String content;
}


