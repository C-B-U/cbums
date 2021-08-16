package com.cbums.core.member.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdateMemberRequest {

    private String password;

    private String department;

    private String nickName;

    private String profileImage;

    private String introduce;

    @NotBlank
    @Length(min = 13, max = 13)
    private String phoneNumber;

}
