package com.woowacourse.tecobrary.renthistory.util;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.renthistory.entity.RentHistory;
import com.woowacourse.tecobrary.renthistory.entity.RentSerial;
import com.woowacourse.tecobrary.renthistory.entity.RentUser;
import com.woowacourse.tecobrary.renthistory.ui.dto.*;
import com.woowacourse.tecobrary.serial.entity.Serial;
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

    public static ReturnHistoryDto toHistoryDto(final RentHistory rentHistory, final LibraryBook libraryBook) {
        return ReturnHistoryDto.builder()
                .id(rentHistory.getId())
                .serial(rentHistory.getSerialNumber())
                .title(libraryBook.getTitle())
                .userId(rentHistory.getUserId())
                .rentDate(rentHistory.getCreatedAt())
                .returnDate(rentHistory.getDeletedAt())
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
