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

    @Column(name = "userId")
    private Long userId;

    public RentUser(Long userId) {
        this.userId = userId;
    }
}
