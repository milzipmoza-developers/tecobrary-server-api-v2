package com.woowacourse.tecobrary.serial.util;

import com.woowacourse.tecobrary.serial.entity.Serial;
import com.woowacourse.tecobrary.serial.entity.SerialInfo;
import com.woowacourse.tecobrary.serial.entity.SerialLibraryBook;
import com.woowacourse.tecobrary.serial.entity.SerialRentStatus;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateResponseDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialResponseDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialsResponseDto;

public class SerialMapper {

    public static Serial toEntity(final SerialCreateRequestDto serialCreateRequestDto) {
        return new Serial(new SerialInfo(serialCreateRequestDto.getSerialNumber()),
                new SerialLibraryBook(serialCreateRequestDto.getBookId()),
                new SerialRentStatus(false));
    }

    public static SerialCreateResponseDto toDto(final Serial serial, final String message) {
        return new SerialCreateResponseDto(message, SerialResponseDto.builder()
                .id(serial.getId())
                .serialNumber(serial.getSerialNumber())
                .bookId(serial.getBookId())
                .status(serial.getStatus())
                .createdAt(serial.getCreatedAt())
                .updatedAt(serial.getUpdatedAt())
                .build());
    }

    public static SerialsResponseDto toDto(final Serial serial) {
        return new SerialsResponseDto(
                serial.getSerialNumber(),
                serial.getStatus()
        );
    }
}
