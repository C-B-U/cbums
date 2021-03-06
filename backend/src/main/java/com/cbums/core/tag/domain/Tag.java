package com.cbums.core.tag.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false)
    private String tagContent;

    @Builder
    public Tag(String tagContent) {
        this.tagContent = tagContent;
    }
}
