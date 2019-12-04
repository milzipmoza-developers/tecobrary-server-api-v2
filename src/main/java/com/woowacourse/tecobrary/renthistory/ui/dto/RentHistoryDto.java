package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class RentHistoryDto {

    private Long id;
    private Long serial;
    private String title;
    private Long userId;
    private LocalDateTime rentDate;

    @Builder
    public RentHistoryDto(final Long id, final Long serial, final String title, final Long userId, final LocalDateTime rentDate) {
        this.id = id;
        this.serial = serial;
        this.title = title;
        this.userId = userId;
        this.rentDate = rentDate;
    }
}
