package com.cbums.core.answer.dto;

import com.cbums.core.form.dto.QuestionRequest;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AnswerRequest {

    @NotNull
    private Long questionId;

    private String content;

}
