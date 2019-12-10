package com.woowacourse.tecobrary.serial.exception;

public class AlreadyReturnStatusException extends RuntimeException {

    public static final String ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE = "이미 반납된 도서입니다.";

    public AlreadyReturnStatusException() {
        super(ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE);
    }
}
