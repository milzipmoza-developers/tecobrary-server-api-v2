package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class RentRequestDto extends RentHistoryRequestDto {

    public RentRequestDto(final Long serial, final Long userId) {
        super(serial, userId);
    }
}
