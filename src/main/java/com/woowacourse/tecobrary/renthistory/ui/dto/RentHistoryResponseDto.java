package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;

@Getter
class RentHistoryResponseDto {

    private RentInfoDto rentInfo;
    private String message;

    RentHistoryResponseDto(final RentInfoDto rentInfo, final String message) {
        this.rentInfo = rentInfo;
        this.message = message;
    }
}

