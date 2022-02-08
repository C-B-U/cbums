package com.cbums.core.study.domain;

import com.cbums.core.tag.domain.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudyTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="study_id", nullable = false)
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id", nullable = false)
    private Tag tag;

    public StudyTag(Study study, Tag tag) {
        this.study = study;
        this.tag = tag;
    }
}
