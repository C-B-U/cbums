package com.cbums.core.form.dto;

import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.Question;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class QuestionResponse {
    private Long questionId;
    private String content;
    private Form form;

    public static QuestionResponse of(Question question) {
        return new QuestionResponse(
                question.getQuestionId(),
                question.getContent(),
                question.getForm());
    }

    public static List<QuestionResponse> listOf(List<Question> questions) {
        return questions.stream()
                .map(QuestionResponse::of)
                .collect(Collectors.toList());
    }
}
