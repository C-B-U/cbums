package com.cbums.core.tag.dto;

import com.cbums.core.tag.domain.Tag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TagResponse {
    private Long tagId;
    private String tagContent;

    public static TagResponse of(Tag tag) {
        return new TagResponse(
                tag.getTagId(),
                tag.getTagContent()
        );
    }

    public static List<TagResponse> lostOf(List<Tag> tags) {
        return tags.stream()
                .map(TagResponse::of)
                .collect(Collectors.toList());
    }
}

