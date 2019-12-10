package com.woowacourse.tecobrary.serial.exception;

public class NotFoundSerialNumberException extends RuntimeException {

    public static final String NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE = "존재하지 않는 일련번호 입니다.";

    public NotFoundSerialNumberException() {
        super(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE);
    }
}
