package com.woowacourse.tecobrary.serial.ui.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SerialCreateResponseDto {

    private String message;
    private SerialResponseDto serial;

    public SerialCreateResponseDto(final String message, final SerialResponseDto serial) {
        this.message = message;
        this.serial = serial;
    }
}
