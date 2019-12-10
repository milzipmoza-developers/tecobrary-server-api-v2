package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class RentRequestDto {

    private Long serial;
    private Long userId;

    public RentRequestDto(final Long serial, final Long userId) {
        this.serial = serial;
        this.userId = userId;
    }
}
