package com.woowacourse.tecobrary.user.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Authorization {
    NONE("NONE"),
    USER("USER"),
    MANAGER("MANAGER"),
    KING("KING");

    private String authorization;

    Authorization(String authorization) {
        this.authorization = authorization;
    }

    public boolean hasUserPermission() {
        return this != NONE;
    }

    public boolean hasManagerPermission() {
        return this != NONE && this != USER;
    }
}
