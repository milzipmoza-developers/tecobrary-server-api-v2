package com.woowacourse.tecobrary.serial.ui.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SerialsResponseDto {

    private Long serialNumber;
    private Boolean status;

    public SerialsResponseDto(final Long serialNumber, final Boolean status) {
        this.serialNumber = serialNumber;
        this.status = status;
    }
}
