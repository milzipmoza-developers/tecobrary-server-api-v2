package com.woowacourse.tecobrary.renthistory.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class RentSerial {

    @Column(name = "serialId",
            nullable = false)
    private Long serialId;

    public RentSerial(final Long serialId) {
        this.serialId = serialId;
    }
}
