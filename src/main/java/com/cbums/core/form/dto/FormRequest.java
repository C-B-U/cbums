package com.cbums.core.form.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FormRequest {

    @NotBlank
    private String title;

    private String introduce;

    @NotNull
    private LocalDateTime openDateTime;

    @NotNull
    private LocalDateTime closeDateTime;

    private Integer registerNumber;

    private String posterImage;

    private List<QuestionRequest> questionRequests;
}
