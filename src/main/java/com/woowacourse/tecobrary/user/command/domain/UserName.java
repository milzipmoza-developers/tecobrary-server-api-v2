package com.woowacourse.tecobrary.user.command.domain;

import com.woowacourse.tecobrary.user.command.domain.exception.UserNameLengthException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserName {

    @Column(name = "name",
            nullable = true,
            length = 100)
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
