package com.woowacourse.tecobrary.serial.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class SerialInfo {

    @Column(name = "serialNumber", unique = true, nullable = false)
    private Long serialNumber;

    public SerialInfo(final Long serialNumber) {
        this.serialNumber = serialNumber;
    }
}
