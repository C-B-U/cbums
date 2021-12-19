package com.cbums.core.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleType {
    ADMIN("ROLE_ADMIN", "관리자"),
    MEMBER("ROLE_MEMBER", "사용자"),
    GUEST("ROLE_GUEST", "게스트");

    private final String key;
    private final String title;
}
