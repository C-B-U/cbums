package com.cbums.core.member.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class MemberAddDetailRequest {
    @NotBlank
    String password;

    @NotBlank
    @Length(min = 1)
    String nickName;

    String profileImage;

    String introduce;
}
