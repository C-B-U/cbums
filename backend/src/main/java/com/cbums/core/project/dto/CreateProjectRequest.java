package com.cbums.core.project.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class CreateProjectRequest {

    @NotBlank
    private String name;

    private Integer maximumMember;

    private LocalDate startDate;
    private LocalDate finishDate;

    private String rule;
    private String additionalExplain;

    @NotNull
    private boolean producerHidden;

}
