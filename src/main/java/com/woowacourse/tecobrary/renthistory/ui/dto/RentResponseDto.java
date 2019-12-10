package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;

@Getter
public class RentResponseDto {

    private RentInfoDto rentInfo;
    private String message;

    public RentResponseDto(final RentInfoDto rentInfo, final String message) {
        this.rentInfo = rentInfo;
        this.message = message;
    }
}
