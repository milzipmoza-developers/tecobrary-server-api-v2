/*
 * @(#) UserAuthorization.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserAuthorization {

    @Enumerated(EnumType.STRING)
    @Column(name = "authorization",
            nullable = false)
    private Authorization authorization;

    public UserAuthorization(final Authorization authorization) {
        this.authorization = authorization;
    }

    public String updateAuthorization(final String newAuthorization) {
        this.authorization = Authorization.of(newAuthorization);
        return this.authorization.getAuthorization();
    }

    String getAuthorizationContent() {
        return authorization.name();
    }
}
