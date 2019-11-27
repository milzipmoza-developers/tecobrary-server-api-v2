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

    @Column(name = "serialId")
    private Long serialId;

    public RentSerial(Long serialId) {
        this.serialId = serialId;
    }
}
