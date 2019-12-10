package com.woowacourse.tecobrary.serial.exception;

public class AlreadyRentStatusException extends RuntimeException {

    public static final String ALREADY_RENT_BOOK_EXCEPTION_MESSAGE = "이미 대여 중인 책입니다.";

    public AlreadyRentStatusException() {
        super(ALREADY_RENT_BOOK_EXCEPTION_MESSAGE);
    }
}
