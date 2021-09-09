package com.cbums.core.tag.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class TagApplyRequest {
    private List<Long> tagList;
}
