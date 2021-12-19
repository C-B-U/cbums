package com.cbums.core.member.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class UpdateNickNameRequest {

    @NotBlank
    private String nickName;

}
