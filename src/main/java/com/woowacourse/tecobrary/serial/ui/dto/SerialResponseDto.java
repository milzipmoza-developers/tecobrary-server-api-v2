package com.woowacourse.tecobrary.serial.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class SerialResponseDto {

    private Boolean status;
    private Long id;
    private Long serialNumber;
    private Long bookId;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @Builder
    private SerialResponseDto(final boolean status, final Long id, final Long serialNumber, final Long bookId, final LocalDateTime updatedAt, final LocalDateTime createdAt) {
        this.status = status;
        this.id = id;
        this.serialNumber = serialNumber;
        this.bookId = bookId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }
}
