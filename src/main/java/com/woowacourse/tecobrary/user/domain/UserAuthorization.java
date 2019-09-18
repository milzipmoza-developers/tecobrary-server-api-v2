package com.woowacourse.tecobrary.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserAuthorization {

    @Column(name = "authorization")
    private Authorization authorization;

    public UserAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    String updateAuthorization(String newAuthorization) {
        this.authorization = Authorization.valueOf(newAuthorization);
        return this.authorization.getAuthorization();
    }
}
