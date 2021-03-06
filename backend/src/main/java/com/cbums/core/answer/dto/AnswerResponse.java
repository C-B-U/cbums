package com.cbums.core.answer.dto;

import com.cbums.core.answer.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private Long answerId;
    private Long memberId;
    private Long questionId;
    private String content;

    public static AnswerResponse of(Answer answer) {
        return new AnswerResponse(
                answer.getAnswerId(),
                answer.getMember().getMemberId(),
                answer.getQuestion().getQuestionId(),
                answer.getContent());
    }

    public static List<AnswerResponse> listOf(List<Answer> answers) {
        return answers.stream()
                .map(AnswerResponse::of)
                .collect(Collectors.toList());
    }
}
