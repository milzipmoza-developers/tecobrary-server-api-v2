package com.woowacourse.tecobrary.serial.util;

import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialInfo;
import com.woowacourse.tecobrary.serial.domain.SerialLibraryBook;
import com.woowacourse.tecobrary.serial.domain.SerialRentStatus;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateResponseDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialResponseDto;

public class SerialMapper {

    public static Serial toEntity(SerialCreateRequestDto serialCreateRequestDto) {
        return new Serial(new SerialInfo(serialCreateRequestDto.getSerialNumber()),
                new SerialLibraryBook(serialCreateRequestDto.getBookId()),
                new SerialRentStatus(false));
    }

    public static SerialCreateResponseDto toDto(Serial serial, String message) {
        return new SerialCreateResponseDto(message, SerialResponseDto.builder()
                .id(serial.getId())
                .serialNumber(serial.getSerialInfo())
                .bookId(serial.getBookId())
                .status(serial.getStatus())
                .createdAt(serial.getCreatedAt())
                .updatedAt(serial.getUpdatedAt())
                .build());
    }
}
