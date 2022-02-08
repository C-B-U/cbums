package com.cbums.core.study.dto;

import com.cbums.core.study.domain.Study;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class StudyResponse {

    private Long projectId;
    private String name;
    private LocalDateTime registerDatetime;
    private Integer maximumMember;
    private String producer;
    private LocalDate startDate;
    private LocalDate finishDate;
    private String rule;
    private String additionalExplain;
    private boolean isRecruit;

    public static StudyResponse of(Study study) {
        return new StudyResponse(
                study.getStudyId(),
                study.getName(),
                study.getRegisterDatetime(),
                study.getMaximumMember(),
                study.getProducer().getName(),
                study.getStartDate(),
                study.getFinishDate(),
                study.getRule(),
                study.getAdditionalExplain(),
                study.getRecruit());
    }

    public static List<StudyResponse> listOf(List<Study> studies) {
        return studies.stream()
                .map(StudyResponse::of)
                .collect(Collectors.toList());
    }

}
