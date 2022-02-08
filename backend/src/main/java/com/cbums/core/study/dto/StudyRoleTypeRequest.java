package com.cbums.core.study.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class StudyRoleTypeRequest {

    @NotNull
    private String studyRoleType;

}
