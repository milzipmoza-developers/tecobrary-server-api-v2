package com.woowacourse.tecobrary.user.command.domain;

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
    @Column(name = "authorization")
    private Authorization authorization;

    public UserAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    String updateAuthorization(String newAuthorization) {
        this.authorization = Authorization.of(newAuthorization);
        return this.authorization.getAuthorization();
    }

    String getAuthorizationContent() {
        return authorization.name();
    }
}
