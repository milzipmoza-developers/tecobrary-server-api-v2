package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;

@Getter
public class RentResponseDto extends RentHistoryResponseDto {

    public RentResponseDto(final RentInfoDto rentInfo, final String message) {
        super(rentInfo, message);
    }
}
