package com.woowacourse.tecobrary.web.renthistory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReturnHistoryInfoDto {

    private Long id;
    private Long serial;
    private String title;
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime rentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime returnDate;

    @Builder
    public ReturnHistoryInfoDto(Long id, Long serial, String title, Long userId, LocalDateTime rentDate, LocalDateTime returnDate) {
        this.id = id;
        this.serial = serial;
        this.title = title;
        this.userId = userId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
}
