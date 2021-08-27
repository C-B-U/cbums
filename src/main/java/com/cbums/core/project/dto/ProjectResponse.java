package com.cbums.core.project.dto;

import com.cbums.core.project.domain.Project;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProjectResponse {

    private Long projectId;
    private String name;
    private LocalDateTime registerDatetime;
    private Integer maximumMember;
    private String producer;
    private boolean isProducerHidden;
    private String icon;
    private Integer registerNumber;
    private boolean isRecruit;

    public static ProjectResponse of(Project project) {
        return new ProjectResponse(
                project.getProjectId(),
                project.getName(),
                project.getRegisterDatetime(),
                project.getMaximumMember(),
                project.getProducer().getName(),
                project.isProducerHidden(),
                project.getIcon(),
                project.getRegisterNumber(),
                project.isRecruit());
    }

    public static List<ProjectResponse> listOf(List<Project> projects) {
        return projects.stream()
                .map(ProjectResponse::of)
                .collect(Collectors.toList());
    }


}
