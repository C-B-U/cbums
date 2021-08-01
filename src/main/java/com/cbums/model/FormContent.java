package com.cbums.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
