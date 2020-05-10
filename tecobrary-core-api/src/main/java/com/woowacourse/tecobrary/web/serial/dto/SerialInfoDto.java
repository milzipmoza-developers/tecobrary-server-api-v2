package com.woowacourse.tecobrary.web.serial.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SerialInfoDto {

    private final Long serialNumber;
    private final Boolean status;

    @Builder
    public SerialInfoDto(Long serialNumber, Boolean status) {
        this.serialNumber = serialNumber;
        this.status = status;
    }
}
