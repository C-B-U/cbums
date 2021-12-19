package com.cbums.core.project.dto;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class ProjectRoleTypeRequest {

    @NotNull
    private String projectRoleType;

}
