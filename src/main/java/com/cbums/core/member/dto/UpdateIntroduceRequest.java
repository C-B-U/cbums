package com.cbums.core.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UpdateIntroduceRequest {

    private String introduce;

}

