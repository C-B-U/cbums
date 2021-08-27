package com.cbums.core.project.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ProjectRequest {

    @NotBlank
    private String name;

    private Integer maximumMember;

    @NotNull
    private boolean isProducerHidden;

    private String icon;

}
