package com.cbums.core.member.dto;

import com.cbums.core.member.domain.MemberTag;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberTagResponse {

    private Long memberTagId;
    private Long tagId;
    private String tagContent;

    public static MemberTagResponse of(MemberTag tag) {
        return new MemberTagResponse(tag.getMemberTagId(),
                tag.getTag().getTagId(),
                tag.getTag().getTagContent());
    }

    public static List<MemberTagResponse> listOf(List<MemberTag> tags) {
        return tags.stream()
                .map(MemberTagResponse::of)
                .collect(Collectors.toList());
    }
}
