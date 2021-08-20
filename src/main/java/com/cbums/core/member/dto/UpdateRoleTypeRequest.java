package com.cbums.core.member.dto;

import com.cbums.core.member.domain.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UpdateRoleTypeRequest {

    @NotBlank
    private UserRoleType userRoleType;
}
