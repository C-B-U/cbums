package com.cbums.core.form.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateFormRequest {

    @NotBlank
    private String title;

    private String introduce;

    @NotBlank
    private LocalDateTime openDateTime;

    @NotBlank
    private LocalDateTime closeDateTime;

    private Integer registerNumber;

}
