package com.cbums.core.form.dto;

import com.cbums.core.form.domain.Question;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateFormRequest {

    @NotBlank
    private String title;

    private String introduce;

    @NotBlank
    private LocalDateTime openDateTime;

    @NotBlank
    private LocalDateTime closeDateTime;

    private Integer registerNumber;

    private List<Question> formQuestionList = new ArrayList<>();

}
