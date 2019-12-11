package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;

public class AlreadyReturnBookException extends RuntimeException {

    public static final String ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE = "이미 반납된 도서입니다.";

    private RentHistoryRequest returnRequestDto;

    public AlreadyReturnBookException() {
        super(ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE);
    }

    public AlreadyReturnBookException(final RentHistoryRequest returnRequestDto) {
        this();
        this.returnRequestDto = returnRequestDto;
    }

    public RentHistoryRequest getReturnRequestDto() {
        return returnRequestDto;
    }
}
