package com.cbums.core.member.dto;

import com.cbums.core.member.domain.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleTypeRequest {

    @NotBlank
    private String userRoleType;
}
