package com.woowacourse.tecobrary.web.renthistory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReturnResponse {

    private final Long serialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime returnedAt;

    @Builder
    public ReturnResponse(Long serialNumber, LocalDateTime returnedAt) {
        this.serialNumber = serialNumber;
        this.returnedAt = returnedAt;
    }
}
