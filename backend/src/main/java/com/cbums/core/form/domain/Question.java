package com.cbums.core.form.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String content;

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

}
