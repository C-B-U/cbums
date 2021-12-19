package com.cbums.core.form.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class QuestionRequest {

    @NotBlank
    private String content;

}
