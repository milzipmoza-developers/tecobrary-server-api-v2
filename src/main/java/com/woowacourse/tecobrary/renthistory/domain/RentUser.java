package com.woowacourse.tecobrary.renthistory.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class RentUser {

    @Column(name = "userId",
            nullable = false)
    private Long userId;

    public RentUser(Long userId) {
        this.userId = userId;
    }

    boolean isSameUser(final Long userId) {
        return this.userId.equals(userId);
    }
}
