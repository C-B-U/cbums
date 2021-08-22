package com.cbums.core.member.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Length(min = 13, max = 13)
    private String phoneNumber;

    @NotBlank
    @Length(min = 10, max = 10)
    private String classNumber;

    @NotBlank
    private String department;
}
