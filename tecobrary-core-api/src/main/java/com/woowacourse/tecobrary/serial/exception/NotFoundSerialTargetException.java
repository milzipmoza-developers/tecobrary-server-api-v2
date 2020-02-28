package com.woowacourse.tecobrary.serial.exception;

public class NotFoundSerialTargetException extends RuntimeException {

    public static final String NOT_FOUND_SERIAL_TARGET_EXCEPTION_MESSAGE = "해당하는 책이 존재하지 않습니다.";

    public NotFoundSerialTargetException() {
        super(NOT_FOUND_SERIAL_TARGET_EXCEPTION_MESSAGE);
    }
}
