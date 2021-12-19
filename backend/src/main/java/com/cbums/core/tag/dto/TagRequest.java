package com.cbums.core.tag.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class TagRequest {
    @NotBlank
    private String tagContent;
}
