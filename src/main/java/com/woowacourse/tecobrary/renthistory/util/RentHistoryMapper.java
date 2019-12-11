package com.woowacourse.tecobrary.renthistory.util;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.renthistory.domain.RentUser;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentInfoDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnInfoDto;
import com.woowacourse.tecobrary.serial.domain.Serial;

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

    public static RentInfoDto toRentInfoDto(final LibraryBook libraryBook,
                                            final RentHistory rentHistory,
                                            final Serial serial) {
        return new RentInfoDto(libraryBook.getTitle(),
                rentHistory.getSerialNumber(),
                serial.getStatus());
    }

    public static RentHistory toEntity(final RentHistoryRequest rentRequestDto) {
        return new RentHistory(
                new RentSerial(rentRequestDto.getSerial()),
                new RentUser(rentRequestDto.getUserId())
        );
    }

    public static ReturnInfoDto toReturnInfoDto(final LibraryBook libraryBook,
                                              final Serial serial,
                                              final RentHistory rentHistory) {
        return
                new ReturnInfoDto(libraryBook.getTitle(),
                        serial.getSerialNumber(),
                        rentHistory.getDeletedAt());
    }
}
