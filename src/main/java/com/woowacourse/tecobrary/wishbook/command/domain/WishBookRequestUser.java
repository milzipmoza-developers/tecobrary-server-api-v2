package com.woowacourse.tecobrary.wishbook.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class WishBookRequestUser {

    @Column(name = "userId",
            nullable = false)
    private Long userId;

    public WishBookRequestUser(Long userId) {
        this.userId = userId;
    }
}
