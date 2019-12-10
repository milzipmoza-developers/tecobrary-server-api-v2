package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
class RentHistoryRequestDto implements RentHistoryRequest {

    private Long serial;
    private Long userId;

    RentHistoryRequestDto(final Long serial, final Long userId) {
        this.serial = serial;
        this.userId = userId;
    }
}
