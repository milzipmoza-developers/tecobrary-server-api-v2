package com.woowacourse.tecobrary.web.renthistory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentHistoryInfoDto {

    private final Long id;
    private final Long serial;
    private final String title;
    private final Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime rentDate;

    @Builder
    public RentHistoryInfoDto(Long id,
                              Long serial,
                              String title,
                              Long userId,
                              LocalDateTime rentDate) {
        this.id = id;
        this.serial = serial;
        this.title = title;
        this.userId = userId;
        this.rentDate = rentDate;
    }
}
