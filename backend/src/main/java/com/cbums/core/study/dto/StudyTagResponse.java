package com.cbums.core.study.dto;

import com.cbums.core.study.domain.StudyTag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StudyTagResponse {
    private Long studyTagId;
    private Long tagId;
    private String tagContent;

    public static StudyTagResponse of(StudyTag tag) {
        return new StudyTagResponse(
                tag.getStudyTagId(),
                tag.getTag().getTagId(),
                tag.getTag().getTagContent()
        );
    }

    public static List<StudyTagResponse> listOf(List<StudyTag> tags) {
        return tags.stream()
                .map(StudyTagResponse::of)
                .collect(Collectors.toList());
    }
}
