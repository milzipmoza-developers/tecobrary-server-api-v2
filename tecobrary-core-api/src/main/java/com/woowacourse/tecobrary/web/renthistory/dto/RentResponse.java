package com.woowacourse.tecobrary.web.renthistory.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RentResponse {

    private final String title;
    private final Long serial;
    private final Boolean status;

    @Builder
    public RentResponse(String title, Long serial, Boolean status) {
        this.title = title;
        this.serial = serial;
        this.status = status;
    }
}
