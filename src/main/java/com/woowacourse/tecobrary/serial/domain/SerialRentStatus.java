package com.woowacourse.tecobrary.serial.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class SerialRentStatus {

    @Column(name = "status")
    private boolean status;

    public SerialRentStatus(boolean status) {
        this.status = status;
    }
}
