package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;

public class NotFoundRentHistoryException extends RuntimeException {

    public static final String NOT_FOUND_RENT_HISTORY_EXCEPTION_MESSAGE = "대여 기록이 존재하지 않습니다.";

    private RentHistoryRequest returnRequestDto;

    public NotFoundRentHistoryException() {
        super(NOT_FOUND_RENT_HISTORY_EXCEPTION_MESSAGE);
    }

    public NotFoundRentHistoryException(RentHistoryRequest returnRequestDto) {
        this();
        this.returnRequestDto = returnRequestDto;
    }

    public RentHistoryRequest getReturnRequestDto() {
        return returnRequestDto;
    }
}
