/*
 * @(#) Authorization.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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

    public static Authorization of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.getAuthorization().equals(name))
                .findFirst()
                .orElseThrow(CannotFoundAuthorizationException::new);
    }

    public boolean hasUserPermission() {
        return this != NONE;
    }

    public boolean hasManagerPermission() {
        return this != NONE && this != USER;
    }
}
