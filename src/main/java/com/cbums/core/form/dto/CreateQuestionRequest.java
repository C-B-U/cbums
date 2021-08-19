package com.cbums.core.form.dto;

import com.cbums.core.form.domain.Form;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateQuestionRequest {

    @NotBlank
    private String content;

    @NotBlank
    private Form form;
}
