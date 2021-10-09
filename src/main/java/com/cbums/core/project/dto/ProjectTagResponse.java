package com.cbums.core.project.dto;

import com.cbums.core.project.domain.ProjectTag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ProjectTagResponse {
    private Long projectTagId;
    private Long tagId;
    private String tagContent;

    public static ProjectTagResponse of(ProjectTag tag) {
        return new ProjectTagResponse(
                tag.getProjectTagId(),
                tag.getTag().getTagId(),
                tag.getTag().getTagContent()
        );
    }

    public static List<ProjectTagResponse> listOf(List<ProjectTag> tags) {
        return tags.stream()
                .map(ProjectTagResponse::of)
                .collect(Collectors.toList());
    }
}
