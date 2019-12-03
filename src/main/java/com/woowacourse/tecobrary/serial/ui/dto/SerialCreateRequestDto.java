package com.woowacourse.tecobrary.serial.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class SerialCreateRequestDto {

    private Long bookId;
    private Long serialNumber;

    public SerialCreateRequestDto(final Long bookId, final Long serialNumber) {
        this.bookId = bookId;
        this.serialNumber = serialNumber;
    }
}
