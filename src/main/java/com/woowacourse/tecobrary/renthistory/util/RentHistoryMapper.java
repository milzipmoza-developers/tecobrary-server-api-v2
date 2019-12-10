package com.woowacourse.tecobrary.renthistory.util;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.renthistory.domain.RentUser;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentInfoDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentResponseDto;
import com.woowacourse.tecobrary.serial.domain.Serial;
import lombok.Builder;

public class RentHistoryMapper {

    public static RentHistoryDto toDto(final RentHistory rentHistory, final LibraryBook libraryBook) {
        return RentHistoryDto.builder()
                .id(rentHistory.getRentNo())
                .serial(rentHistory.getSerialNumber())
                .title(libraryBook.getTitle())
                .userId(rentHistory.getUserId())
                .rentDate(rentHistory.getCreatedAt())
                .build();
    }

    @Builder
    public static RentResponseDto toDto(final Serial serial, final RentHistory rentHistory, final LibraryBook libraryBook, final String message) {
        return new RentResponseDto(new RentInfoDto(libraryBook.getTitle(), rentHistory.getSerialNumber(), serial.getStatus()), message);
    }

    public static RentHistory toEntity(final RentHistoryRequest rentRequestDto) {
        return new RentHistory(
                new RentSerial(rentRequestDto.getSerial()),
                new RentUser(rentRequestDto.getUserId())
        );
    }
}
