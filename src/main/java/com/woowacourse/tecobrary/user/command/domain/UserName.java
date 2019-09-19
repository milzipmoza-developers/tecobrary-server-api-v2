package com.woowacourse.tecobrary.user.command.domain;

import com.woowacourse.tecobrary.user.command.domain.exception.UserNameLengthException;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserName {

    private String name;

    public UserName(String name) {
        checkNameLength(name);
        this.name = name;
    }

    private void checkNameLength(String name) {
        if (name.length() > 255) {
            throw new UserNameLengthException();
        }
    }
}
