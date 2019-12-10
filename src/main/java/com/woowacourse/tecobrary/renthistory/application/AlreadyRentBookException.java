package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import com.woowacourse.tecobrary.serial.exception.AlreadyRentStatusException;

public class AlreadyRentBookException extends AlreadyRentStatusException {

    public static final String ALREADY_RENT_BOOK_EXCEPTION_MESSAGE = "이미 대여 중인 책입니다.";

    private RentRequestDto rentRequestDto;

    public AlreadyRentBookException(final RentRequestDto rentRequestDto) {
        super();
        this.rentRequestDto = rentRequestDto;
    }

    public RentRequestDto getRentRequestDto() {
        return rentRequestDto;
    }
}
