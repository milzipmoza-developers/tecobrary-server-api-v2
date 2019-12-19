package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RentInfoDto {

    private String title;
    private Long serialNumber;
    private Boolean status;

    public RentInfoDto(final String title, final Long serialNumber, final Boolean status) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.status = status;
    }
}
