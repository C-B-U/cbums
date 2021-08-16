package com.cbums.core.member.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberAddDetailRequest {
    @NotBlank
    String password;

    String profileImage;

    String introduce;
}
