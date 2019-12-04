package com.woowacourse.tecobrary.renthistory.util;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryDto;

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
}
