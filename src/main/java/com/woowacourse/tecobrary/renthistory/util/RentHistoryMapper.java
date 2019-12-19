package com.woowacourse.tecobrary.renthistory.util;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.renthistory.domain.RentUser;
import com.woowacourse.tecobrary.renthistory.ui.dto.*;
import com.woowacourse.tecobrary.serial.domain.Serial;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentHistoryMapper {

    public static RentHistoryDto toDto(final RentHistory rentHistory, final LibraryBook libraryBook) {
        return RentHistoryDto.builder()
                .id(rentHistory.getId())
                .serial(rentHistory.getSerialNumber())
                .title(libraryBook.getTitle())
                .userId(rentHistory.getUserId())
                .rentDate(rentHistory.getCreatedAt())
                .build();
    }

    @Builder(builderMethodName = "rentDtoBuilder")
    public static RentResponseDto toRentInfoDto(final LibraryBook libraryBook,
                                                final RentHistory rentHistory,
                                                final Serial serial,
                                                final String message) {
        return new RentResponseDto(
                new RentInfoDto(libraryBook.getTitle(),
                        rentHistory.getSerialNumber(),
                        serial.getStatus()),
                message);
    }

    public static RentHistory toEntity(final RentHistoryRequest rentRequestDto) {
        return new RentHistory(
                new RentSerial(rentRequestDto.getSerial()),
                new RentUser(rentRequestDto.getUserId())
        );
    }

    @Builder(builderMethodName = "returnDtoBuilder")
    public static ReturnResponseDto toReturnInfoDto(final LibraryBook libraryBook,
                                                    final Serial serial,
                                                    final RentHistory rentHistory,
                                                    final String message) {
        return new ReturnResponseDto(
                new ReturnInfoDto(libraryBook.getTitle(),
                        serial.getSerialNumber(),
                        rentHistory.getDeletedAt()),
                message);
    }
}
