package com.woowacourse.tecobrary.web.renthistory.service;

import com.woowacourse.tecobrary.domain.renthistory.entity.RentHistory;
import com.woowacourse.tecobrary.web.renthistory.dto.RentHistoryInfoDto;
import com.woowacourse.tecobrary.web.renthistory.dto.RentResponse;
import com.woowacourse.tecobrary.web.renthistory.dto.ReturnHistoryInfoDto;
import com.woowacourse.tecobrary.web.renthistory.dto.ReturnResponse;
import org.springframework.stereotype.Component;

@Component
public class RentHistoryConverter {

    public RentHistoryInfoDto convertInfoDto(RentHistory history) {
        return RentHistoryInfoDto.builder()
                .id(history.getId())
                .serial(history.getSerial().getSerialNumber())
                .title(history.getSerial().getLibraryBook().getTitle())
                .userId(history.getUser().getId())
                .rentDate(history.getCreatedAt())
                .build();
    }

    public ReturnHistoryInfoDto convertReturnInfoDto(RentHistory history) {
        return ReturnHistoryInfoDto.builder()
                .id(history.getId())
                .serial(history.getSerial().getSerialNumber())
                .title(history.getSerial().getLibraryBook().getTitle())
                .userId(history.getUser().getId())
                .rentDate(history.getCreatedAt())
                .returnDate(history.getDeletedAt())
                .build();
    }

    public RentResponse convertRentResponse(RentHistory history) {
        return RentResponse.builder()
                .title(history.getSerial().getLibraryBook().getTitle())
                .serial(history.getSerial().getSerialNumber())
                .status(history.getSerial().isStatus())
                .build();
    }

    public ReturnResponse convertReturnResponse(RentHistory history) {
        return ReturnResponse.builder()
                .serialNumber(history.getSerial().getSerialNumber())
                .returnedAt(history.getDeletedAt())
                .build();
    }
}
